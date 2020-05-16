package javalab11.lab;

import javalab11.lab.CustomExceptions.GameNotFound;
import javalab11.lab.CustomExceptions.PlayerNotFound;
import javalab11.lab.CustomExceptions.RestAdvice;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ServicesController
{
     @Autowired
     private PlayersRepository playersRepository;
     @Autowired
     private GamedataRepository gamedataRepository;

     private final AtomicInteger gameCounter = new AtomicInteger();
     private final AtomicInteger playerCounter = new AtomicInteger();

    @RequestMapping("/insertPlayer")
     public @ResponseBody String insertPlayer(@RequestParam String name)
     {
         Players newPlayer = new Players();
         newPlayer.setName(name);
         newPlayer.setId(playerCounter.incrementAndGet());

         Gamedata currentGame = new Gamedata();

         if (gameCounter.get() == 0)
         {
             currentGame.setId(gameCounter.incrementAndGet());
             currentGame.setNumberOfPlayers(1);
         }
         else
         {

             newPlayer.setGameId(gameCounter.get());
             currentGame = gamedataRepository.findById(newPlayer.getGameId()).get();
             currentGame.setNumberOfPlayers(currentGame.getNumberOfPlayers() + 1);
         }
         gamedataRepository.save(currentGame);

         playersRepository.save(newPlayer);
         return "Succes";
     }

     @RequestMapping("/modifyName")
     public @ResponseBody String modifyName(@RequestParam int id, @RequestParam String name)
     {
         Players newPlayer = new Players();
         newPlayer = playersRepository.findById(id).get();
         newPlayer.setName(name);
         playersRepository.save(newPlayer);
         return "Succes";
     }

     @RequestMapping("/deletePlayer")
     public @ResponseBody String deletePlayer(@RequestParam int id)
     {
         Players player = new Players();

         if (!playersRepository.findById(id).isPresent())
         {
             throw new PlayerNotFound("Can't find player with ID : " + id + " try to list all players to see the one you are looking for");
         }
         else
         {
             player = playersRepository.findById(id).get();

             Gamedata game = new Gamedata();
             game = gamedataRepository.findById(player.getGameId()).get();
             game.setNumberOfPlayers(game.getNumberOfPlayers() - 1);

             gamedataRepository.save(game);
             playersRepository.delete(player);
             return "Deleted";
         }
     }

     @RequestMapping("/listPlayers")
     public List<Players> getAllPlayers()
     {
         if (playersRepository.count() == 0)
         {
             throw new PlayerNotFound("There are no players in this application, try adding some");
         }
         else
         {
             return Lists.newArrayList(playersRepository.findAll());
         }
     }

    @RequestMapping("/getPlayer")
    public Players getPlayer(@RequestParam int ID)
    {
        if (!playersRepository.findById(ID).isPresent())
        {
            throw new PlayerNotFound("Can't find player with ID : " + ID + " try to list all players to see the one you are looking for");
        }
        else
        {
            return playersRepository.findById(ID).get();
        }
    }

    @RequestMapping("/insertGame")
    public String insertGame()
    {
        Gamedata newGame = new Gamedata();
        newGame.setId(gameCounter.incrementAndGet());
        System.out.println(gameCounter.get());
        gamedataRepository.save(newGame);
        return "Succes";
    }

    @GetMapping("/getGames")
    public List<Gamedata> readingGames()
    {
        if (gamedataRepository.count() == 0)
        {
            throw new GameNotFound("There are no games in this application, try adding some");
        }
        else
        {
            return Lists.newArrayList(gamedataRepository.findAll());
        }
    }

    @GetMapping("/getGame")
    public Gamedata readingGame(@RequestParam int id)
    {
        if (!gamedataRepository.findById(id).isPresent())
        {
            throw new GameNotFound("Can't find game with ID : " + id + " try to list all players to see the one you are looking for");
        }
        else
        {
            return gamedataRepository.findById(id).get();
        }
    }


    @RequestMapping("/deleteGame")
    public @ResponseBody String deleteGame(@RequestParam int id)
    {
        Gamedata game;
        if (!gamedataRepository.findById(id).isPresent())
        {
            throw new GameNotFound("Can't find a game with ID : " + id + " try to list all games to see the one you are looking for");
        }
        else
        {
            game = gamedataRepository.findById(id).get();

            List<Players> players = Lists.newArrayList(playersRepository.findAll());
            for (Players player : players) {
                if (player.getGameId() == game.getId()) {
                    playersRepository.delete(player);
                }
            }

            gamedataRepository.delete(game);
            return "Deleted";
        }
    }

}
