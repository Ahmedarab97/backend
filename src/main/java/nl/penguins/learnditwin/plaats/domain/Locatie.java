package nl.penguins.learnditwin.plaats.domain;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Getter
public abstract class Locatie {
    @MongoId(FieldType.STRING)
    @Field("regioCode_id")
    @Indexed(unique = true)
    protected String regioCode_id;
    protected String naam;
    protected int aantalInwoners;
    protected LocatieInfo locatieInfo;

    public Locatie(String regioCode, String naam) {
        this.regioCode_id = regioCode;
        this.naam = naam;
        this.locatieInfo = new LocatieInfo();
    }

    protected Locatie() {
    }

    public void setAantalHuishoudens(int aantalInwoners) {
        this.aantalInwoners = aantalInwoners;
    }

    public abstract LaagGeletterdheid getLaagGeletterdheid();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locatie locatie = (Locatie) o;

        if (!Objects.equals(regioCode_id, locatie.regioCode_id))
            return false;
        return Objects.equals(naam, locatie.naam);
    }

    @Override
    public int hashCode() {
        int result = regioCode_id != null ? regioCode_id.hashCode() : 0;
        result = 31 * result + (naam != null ? naam.hashCode() : 0);
        return result;
    }
}
