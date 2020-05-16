package javalab11.lab.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestAdvice extends Throwable
{
    @ExceptionHandler(value = PlayerNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String PlayerNotFoundException(PlayerNotFound ex)
    {
         return ex.getMessage();
    }

    @ExceptionHandler(value = GameNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String GameNotFoundException(GameNotFound ex)
    {
        return ex.getMessage();
    }

    @ExceptionHandler(value = NoPlayersInDB.class)
    public String NoPlayersInDBException(NoPlayersInDB ex)
    {
        return ex.getMessage();
    }
}
