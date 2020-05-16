package javalab11.lab;

import javalab11.lab.Gamedata;
import org.springframework.data.repository.CrudRepository;

public interface GamedataRepository extends CrudRepository<Gamedata, Integer> {

}