package nl.penguins.learnditwin.plaats.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wijk_buurten")
public class Wijk {
    private ObjectId _id;
    private String naam;
    private String postcode4;
    private List<Buurt> buurten;

    protected Wijk(){}
    public Wijk(String naam, String postcode4, List<Buurt> buurten) {
        this.naam = naam;
        this.postcode4 = postcode4;
        this.buurten = buurten;
    }

    public static Wijk generateWijk(String naam, String postcode4, List<Buurt> buurten) {
        return new Wijk(naam, postcode4, buurten);
    }
    public void addBuurt(Buurt buurt) {
        this.buurten.add(buurt);
    }

    public String getNaam() {
        return naam;
    }

    public String getPostcode4() {
        return postcode4;
    }

    public List<Buurt> getBuurten() {
        return buurten;
    }
}
