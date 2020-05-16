package javalab11.lab;

import javax.persistence.*;

@Entity
public class Players
{
    private int id;
    private String name;
    private int gameId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "game_id", nullable = false)
    public int getGameId()
    {
        return gameId;
    }

    public void setGameId(int gameId)
    {
        this.gameId = gameId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Players players = (Players) o;

        if (id != players.id) return false;
        if (gameId != players.gameId) return false;
        if (name != null ? !name.equals(players.name) : players.name != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + gameId;
        return result;
    }
}
