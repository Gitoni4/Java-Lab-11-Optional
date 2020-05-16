package javalab11.lab.CustomExceptions;

public class NoPlayersInDB extends RuntimeException
{
    public NoPlayersInDB(String msg)
    {
        super(msg);
    }
}
