package nl.penguins.learnditwin.layers.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import nl.penguins.learnditwin.layers.domain.interfaces.Indicator;

@Entity
public class Fitheid implements Indicator {
    @Id
    @GeneratedValue
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
