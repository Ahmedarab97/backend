package nl.penguins.learnditwin.plaats.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

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
    private LaagGeletterdheid laagGeletterdheid;

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

    public void setLaagGeletterdheid(LaagGeletterdheid laagGeletterdheid){
        this.laagGeletterdheid = laagGeletterdheid;
    }

    public String getNaam() {
        return naam;
    }

    public int getAantalInwoners() {
        return aantalInwoners;
    }

    public void setAantalInwoners(int aantalInwoners) {
        this.aantalInwoners = aantalInwoners;
    }

    public String getBuurtCode_id() {
        return buurtCode_id;
    }

    public List<String> getPostcode6() {
        return postcode6;
    }

    public LaagGeletterdheid getLaagGeletterdheid() {
        return laagGeletterdheid;
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