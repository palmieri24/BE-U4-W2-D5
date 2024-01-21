package it.epicode.Classes;

import it.epicode.Enum.Periodicity;

public class Magazine extends Catalog {
private Periodicity periodicity;

public Magazine(String title, int yearOfPublication, int numberOfPages, Periodicity periodicity){
    super(title, yearOfPublication, numberOfPages);
    this.periodicity = periodicity;
}

public Periodicity getPeriodicity(){
    return periodicity;
}

public void setPeriodicity(Periodicity periodicity){
    this.periodicity = periodicity;
}


@Override
public String toString() {
    return "Magazine : {" +
            "ISBN=" + ISBN +
            ", title='" + title + '\'' +
            ", yearOfPublication=" + yearOfPublication +
            ", numberOfPage=" + numberOfPages +
            ", periodicity=" + periodicity +
            '}';
}


}
