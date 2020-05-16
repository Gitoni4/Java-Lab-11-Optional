package javalab11.lab.CustomExceptions;

public class PlayerNotFound extends RuntimeException
{
    public PlayerNotFound(String msg)
    {
        super(msg);
    }
}
