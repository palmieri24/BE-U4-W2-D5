package it.epicode.Exceptions;

public class CatalogException extends Exception{
    public CatalogException(){
        super("Elemento non disponibile nel catalogo!");
    }
}
