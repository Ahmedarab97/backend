package nl.penguins.learnditwin.plaats.domain;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.LaagGeletterdheid;
import nl.penguins.learnditwin.plaats.domain.ids.RegioCode;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Buurt extends Locatie {
    private List<String> postcode6;

    public Buurt(RegioCode regioCode_id, String naam) {
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
//
//    @Override
//    public double getPercentageFlatBewoners() {
//        return super.getLocatieInfo().getWoonOmstandigheden().getPercentageFlatBewoners();
//    }
}