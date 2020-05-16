package javalab11.lab.CustomExceptions;

import javalab11.lab.Gamedata;

public class GameNotFound extends RuntimeException
{
    public GameNotFound(String msg) { super(msg); }
}
