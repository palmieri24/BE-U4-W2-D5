package it.epicode.Classes;

import java.util.Random;

public class Catalog {
    protected long ISBN;
    private Random random = new Random();
    protected String title;
    protected int yearOfPublication;
    protected int numberOfPages;

public Catalog(String title, int yearOfPublication, int numberOfPages){
    this.ISBN = random.nextLong(0, 999999);
    this.title = title;
    this.yearOfPublication = yearOfPublication;
    this.numberOfPages = numberOfPages;
}

public long getISBN(){
    return ISBN;
}

public void setISBN(long isbn){
    ISBN = isbn;
}

public String getTitle(){
    return title;
}

public void setTitle(String title){
    this.title = title;
}

public int getYearOfPublication(){
    return yearOfPublication;
}

public void setYearOfPublication(){
    this.yearOfPublication = yearOfPublication;
}

public int getNumberOfPages(){
    return numberOfPages;
}

public void setNumberOfPages(int numberOfPages){
    this.numberOfPages = numberOfPages;
}

@Override
    public String toString(){
    return "Catalog : {" +
            "ISBN=" + ISBN +
            ", title='" + title + '\'' +
            ", yearOfPublication=" + yearOfPublication +
            ", numberOfPage=" + numberOfPages +
            '}';
}

}
