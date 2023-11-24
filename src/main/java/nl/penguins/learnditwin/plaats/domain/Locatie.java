package nl.penguins.learnditwin.plaats.domain;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.*;
import nl.penguins.learnditwin.plaats.domain.ids.RegioCode;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter
public abstract class Locatie {
    @Id
    protected RegioCode regioCode_id;
    protected String naam;
    protected int aantalInwoners;
    protected LocatieInfo locatieInfo;

    public Locatie(RegioCode regioCode_id, String naam) {
        this.regioCode_id = regioCode_id;
        this.naam = naam;
        this.locatieInfo = new LocatieInfo();
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
