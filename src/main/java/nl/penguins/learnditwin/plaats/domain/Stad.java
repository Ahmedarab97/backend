package nl.penguins.learnditwin.plaats.domain;

import java.util.ArrayList;

public class Stad {
    private String naam;
    private ArrayList<Plaats> plaatsen;

    public Stad(String naam, ArrayList<Plaats> plaatsen) {
        this.naam = naam;
        this.plaatsen = plaatsen;
    }

    public String getNaam() {
        return naam;
    }

    public ArrayList<Plaats> getPlaatsen() {
        return plaatsen;
    }

}
