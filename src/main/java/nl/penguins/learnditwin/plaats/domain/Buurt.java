package nl.penguins.learnditwin.plaats.domain;

import nl.penguins.learnditwin.plaats.domain.interfaces.Data;

import java.util.List;

public class Buurt {

    private String naam;
    private int aantalInwoners;
    private List<String> postcode6;
    private List<Data> datasets; //hier moeten dus alle data sets inkomen die met klasse die Data interface implementeren denk dat het logisch is als we hem dus indentificeren met de naam als string

    protected Buurt(){}

    public Buurt(String naam, int aantalInwoners, List<String> postcode6) {
        this.naam = naam;
        this.aantalInwoners = aantalInwoners;
        this.postcode6 = postcode6;
    }

    public void addStraat(String postcode6) {
        this.postcode6.add(postcode6);
    }

    public String getNaam() {
        return naam;
    }

    public int getAantalInwoners() {
        return aantalInwoners;
    }

    public List<String> getPostcode6() {
        return postcode6;
    }
}
