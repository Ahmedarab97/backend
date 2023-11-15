package nl.penguins.learnditwin.plaats.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Wijk {
    @MongoId(FieldType.STRING)
    @Field("wijkCode_id")
    @Indexed(unique = true)
    private String wijkCode_id;
    private String naam;
    private String postcode4;
    @DBRef
    private Set<Buurt> buurten = new HashSet<>();

    protected Wijk(){}
    public Wijk(String naam, String postcode4, Set<Buurt> buurten) {
        this.naam = naam;
        this.postcode4 = postcode4;
        this.buurten = buurten;
    }

    public Wijk(String wijkCode, String naam, String postcode4) {
        this.wijkCode_id = wijkCode;
        this.naam = naam;
        this.postcode4 = postcode4;
    }

    public static Wijk generateWijk(String naam, String postcode4, Set<Buurt> buurten) {
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

    public Set<Buurt> getBuurten() {
        return buurten;
    }

    public String getWijkCode_id() {
        return wijkCode_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wijk wijk = (Wijk) o;
        return Objects.equals(wijkCode_id, wijk.wijkCode_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wijkCode_id);
    }
}
