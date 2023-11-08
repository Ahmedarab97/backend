package nl.penguins.learnditwin.plaats.domain;

import nl.penguins.learnditwin.plaats.domain.interfaces.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Buurt {
    @MongoId(FieldType.STRING)
    @Field("buurtCode_id")
    @Indexed(unique = true)
    private String buurtCode_id;
    private String naam;
    private int aantalInwoners;
    private List<String> postcode6 = new ArrayList<>();
    private List<Data> datasets; //hier moeten dus alle data sets inkomen die met klasse die Data interface implementeren denk dat het logisch is als we hem dus indentificeren met de naam als string

    protected Buurt() {
    }

    public Buurt(String naam, int aantalInwoners, List<String> postcode6) {
        this.naam = naam;
        this.aantalInwoners = aantalInwoners;
        this.postcode6 = postcode6;
    }

    public Buurt(String buurtCode_id, String naam) {
        this.buurtCode_id = buurtCode_id;
        this.naam = naam;
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

    public String getBuurtCode_id() {
        return buurtCode_id;
    }

    public List<String> getPostcode6() {
        return postcode6;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buurt buurt = (Buurt) o;
        return Objects.equals(buurtCode_id, buurt.buurtCode_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buurtCode_id);
    }
}