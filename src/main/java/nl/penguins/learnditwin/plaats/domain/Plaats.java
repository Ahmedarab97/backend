package nl.penguins.learnditwin.plaats.domain;

import java.util.ArrayList;

public class Plaats{
    private String naam;
//    private String Stad;
    private ArrayList<String> postcodes;

    public Plaats(String naam
//    , String Stadsnaam
    ) {
        this.naam = naam;
//        this.Stad = Stadsnaam;
    };
    public String getNaam() {
        return naam;
    };

    public ArrayList<String> getPostcodes(){
        return postcodes;
    };

//    public String getStad() {
//        return Stad;
//    }

}