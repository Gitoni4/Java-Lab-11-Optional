package javalab11.lab;

import javalab11.lab.Players;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends CrudRepository <Players, Integer> {

}