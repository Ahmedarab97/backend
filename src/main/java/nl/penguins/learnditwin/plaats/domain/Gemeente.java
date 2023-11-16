package nl.penguins.learnditwin.plaats.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gemeente {
    @MongoId(FieldType.STRING)
    @Field("wijkCode_id")
    @Indexed(unique = true)
    private final String gemeenteCode_id;
    private String naam;
    @DBRef
    private Set<Wijk> wijken;

    public Gemeente(String gemeenteCode_id, String naam) {
        this.gemeenteCode_id = gemeenteCode_id;
        this.naam = naam;
        this.wijken = new HashSet<>();
    }

    public String getGemeenteCode_id() {
        return gemeenteCode_id;
    }

    public Set<Wijk> getWijken() {
        return wijken;
    }

    public void voegWijkToe(Wijk wijk) {
        wijken.add(wijk);
    }
}
