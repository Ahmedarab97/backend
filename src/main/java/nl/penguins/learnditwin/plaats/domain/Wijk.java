package nl.penguins.learnditwin.plaats.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Document(collection = "wijk_buurten")
public class Wijk {
    @MongoId(FieldType.STRING)
    @Field("wijkCode_id")
    @Indexed(unique = true)
    private String wijkCode_id;
    private String naam;
    private String postcode4;
    private Set<String> BuurtIds = new HashSet<>();

    protected Wijk(){}
    public Wijk(String naam, String postcode4, Set<String> BuurtIds) {
        this.naam = naam;
        this.postcode4 = postcode4;
        this.BuurtIds = BuurtIds;
    }

    public Wijk(String wijkCode, String naam, String postcode4) {
        this.wijkCode_id = wijkCode;
        this.naam = naam;
        this.postcode4 = postcode4;
    }

    public static Wijk generateWijk(String naam, String postcode4, Set<String> buurten) {
        return new Wijk(naam, postcode4, buurten);
    }
    public void addBuurt(String buurtId) {
        this.BuurtIds.add(buurtId);
    }

    public String getNaam() {
        return naam;
    }

    public String getPostcode4() {
        return postcode4;
    }

    public Set<String> getBuurtIds() {
        return BuurtIds;
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
