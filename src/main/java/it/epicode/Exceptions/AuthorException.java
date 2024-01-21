package it.epicode.Exceptions;

public class AuthorException extends Exception{
    public AuthorException(){
        super("Non ci sono libri scritti da questo autore!");
    }
}
