package it.epicode.Classes;

public class Book extends Catalog {
    private String authorName;

    public Book(String title, int yearOfPublication, int numberOfPages, String authorName, String genre) {
        super(title, yearOfPublication, numberOfPages);
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    @Override
    public String toString() {
        return "Book{" +
                " ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPages=" + numberOfPages +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}