package nl.penguins.learnditwin.layers.domain;

import nl.penguins.learnditwin.layers.domain.interfaces.Indicator;

public class Fitheid implements Indicator {
    private Long id;
    private String kleur;

    public Fitheid(String kleur) {
        this.kleur = kleur;
    }

    protected Fitheid() {

    }

    public String getKleur() {
        return kleur;
    }
}
