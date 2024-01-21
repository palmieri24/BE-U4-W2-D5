package it.epicode;

import it.epicode.Classes.Book;
import it.epicode.Classes.Catalog;
import it.epicode.Classes.Magazine;
import it.epicode.Enum.Periodicity;
import it.epicode.Exceptions.AuthorException;
import it.epicode.Exceptions.CatalogException;
import it.epicode.Exceptions.ISBNException;
import it.epicode.Exceptions.YearException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("Progetto: Catalogo Bibliografico");
        File file = new File("src/output.txt");
        List<Catalog> archive = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        int inputChoose = 0;

        try {
            while (true){
            System.out.println("Inserisci 1 per aggiungere un elemento all'archivio, 2 per rimuovere un elemento tramite ISBN,3 per cercare un elemento tramite ISBN,4 per cercare un elemento tramite l'anno");
            System.out.println("Inserisci 5 per cercare un libro tramite il suo autore,6 per salvare l'archivio sul disco, 7 per leggere l'archivio salvato sul disco, 0 per interrompere l'operazione.");
            inputChoose = Integer.parseInt(input.nextLine());

            switch (inputChoose) {
                case 0:
                    break;
                case 1:
                    try {
                        System.out.println("Inserisci 'book' per aggiungere un libro, 'magazine' per aggiungere una rivista");
                        String create = input.nextLine();
                        //Creare un book
                        if (create.toLowerCase().trim().equals("book")) {
                            System.out.println("Inserisci il titolo del libro: ");
                            String title = input.nextLine();
                            System.out.println("Inserisci l'anno di pubblicazione: ");
                            int yearOfPublication = Integer.parseInt(input.nextLine());
                            System.out.println("Inserisci il n° di pagine: ");
                            int numberOfPages = Integer.parseInt(input.nextLine());
                            System.out.println("Inserisci il nome dell'autore: ");
                            String author = input.nextLine();
                            System.out.println("Inserisci il genere: ");
                            String genre = input.nextLine();
                            Book book = new Book(title, yearOfPublication, numberOfPages, author, genre);
                            archive.add(book);
                        }  //Creare un magazine
                        else if (create.toLowerCase().trim().equals("magazine")) {
                            System.out.println("Inserisci il titolo della rivista: ");
                            String title = input.nextLine();
                            System.out.println("Inserisci l'anno di pubblicazione: ");
                            int yearOfPublication = Integer.parseInt(input.nextLine());
                            System.out.println("Inserisci il n° di pagine: ");
                            int numberOfPages = Integer.parseInt(input.nextLine());
                            System.out.println("Inserisci la periodicità (scegli fra settimanale,mensile,semestrale): ");
                            String periodicity = input.nextLine();
                            if (periodicity.toLowerCase().trim().equals("settimanale")) {
                                Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, Periodicity.WEEKLY);
                                archive.add(magazine);
                            } else if (periodicity.toLowerCase().trim().equals("mensile")) {
                                Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, Periodicity.MONTHLY);
                                archive.add(magazine);
                            } else if (periodicity.toLowerCase().trim().equals("semestrale")) {
                                Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, Periodicity.HALFYEAR);
                                archive.add(magazine);
                            }
                        } else {
                            throw new CatalogException();
                        }
                    } catch (CatalogException e) {
                        System.out.println("ERRORE : " + e.getMessage());
                    }
                    break;
                //Rimozione elemento dato un codice ISBN:
                case 2:
                    System.out.println("Inserisci il codice ISBN per rimuovere l'elemento dall'archivio: ");
                    long ISBN = Integer.parseInt(input.nextLine());
                    archive.removeIf(element -> element.getISBN() == ISBN);
                    break;
                //Ricerca tramite ISBN:
                case 3:
                    try {
                        System.out.println("Inserisci il codice ISBN per cercare l'elemento nell'archivio: ");
                        long inputISBNCerca = Integer.parseInt(input.nextLine());
                        List<Catalog> isbn = archive.stream().filter(element -> element.getISBN() == inputISBNCerca).toList();
                        if (isbn.isEmpty()) {
                            throw new ISBNException();
                        } else {
                            System.out.println(isbn);
                        }
                    } catch (ISBNException e) {
                        System.out.println("ERRORE : " + e.getMessage());
                    }
                    break;
                //Ricerca per anno di pubblicazione
                case 4:
                    try {
                        System.out.println("Inserisci un anno per cercare l'elemento nell'archivio: ");
                        int inputAnnoCerca = Integer.parseInt(input.nextLine());
                        List<Catalog> year = archive.stream().filter(element -> element.getYearOfPublication() == inputAnnoCerca).toList();
                        if (year.isEmpty()) {
                            throw new YearException();
                        } else {
                            System.out.println(year);
                        }
                    } catch (YearException e) {
                        System.out.println("ERRORE : " + e.getMessage());
                    }

                    break;
                //Ricerca per Autore:
                case 5:
                    try {
                        System.out.println("Inserisci un autore per cercare l'elemento nell'archivio: ");
                        String inputAutore = input.nextLine();
                        List<Book> catalog = new ArrayList<>();
                        for (int i = 0; i < archive.size(); i++) {
                            if (archive.get(i) instanceof Book) {
                                catalog.add((Book) archive.get(i));
                            }
                        }
                        List<Book> outputAutore = catalog.stream().filter(element -> element.getAuthorName().equals(inputAutore)).toList();
                        if (outputAutore.isEmpty()) {
                            throw new AuthorException();
                        } else {
                            System.out.println(outputAutore);
                        }
                    } catch (AuthorException e) {
                        System.out.println("ERRORE : " + e.getMessage());
                    }
                    break;
                //Salvataggio su disco dell'archivio:
                case 6:
                    try {
                        FileUtils.writeStringToFile(file, archive.toString(), StandardCharsets.UTF_8);
                        break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                case 7:
                    try {
                        String filetext = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                        System.out.println(filetext);
                        break;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                default:
                    throw new Exception();
            } } } catch (Exception e) {
            System.out.println("ERRORE : " + e.getMessage());
        }
        }
    }
