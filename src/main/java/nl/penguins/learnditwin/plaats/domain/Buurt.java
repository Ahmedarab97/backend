package nl.penguins.learnditwin.plaats.domain;

import nl.penguins.learnditwin.plaats.domain.buurtinfo.LaagGeletterdheid;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("buurt")
public class Buurt extends Locatie {
    private int aantalHuishoudens;
    private List<String> postcode6;

    protected Buurt() {
        super();
    }

    public Buurt(String regioCode_id, String naam) {
        super(regioCode_id, naam);
        this.postcode6 = new ArrayList<>();
    }

    public void toevoegenPostcode6(String postcode6) {
        this.postcode6.add(postcode6);
    }

    @Override
    public LaagGeletterdheid getLaagGeletterdheid() {
        return super.getLocatieInfo().getLaagGeletterdheid();
    }

    public void setAantalHuishoudens(int aantalHuishoudens) {
        this.aantalHuishoudens = aantalHuishoudens;
    }

    public List<String> getPostcode6() {
        return postcode6;
    }

    @Override
    public int getAantalInwoners() {
        return this.aantalHuishoudens;
    }
}