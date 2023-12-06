package nl.penguins.learnditwin.plaats.domain;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.LaagGeletterdheid;
import nl.penguins.learnditwin.plaats.domain.ids.RegioCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Getter
@Document(collection = "gemeente")
public class Gemeente extends Locatie {
    private Set<Wijk> wijken;

    /**
     *
     * @param regioCode_id :
     * @param naam
     */
    public Gemeente(RegioCode regioCode_id, String naam) {
        super(regioCode_id, naam);
        this.wijken = new HashSet<>();
    }

    public Wijk getWijkByWijkNaam(String wijkNaam){
        return wijken.stream()
                .filter(wijk -> wijk.getNaam().equals(wijkNaam))
                .findFirst()
                .orElseThrow();
    }

    public Locatie getLocatieByRegioCode(RegioCode regioCode){
        if (regioCode.isGemeente()){
            return this;
        }

        if (regioCode.isWijk()){
            return getWijkByWijkId(regioCode);
        } else {
            return getWijkByWijkId(regioCode.getWijkCode())
                    .getBuurtByRegioCode(regioCode);
        }
    }

    private Wijk getWijkByWijkId(RegioCode wijkCode){
        return wijken.stream()
                .filter(w -> w.getRegioCode_id().equals(wijkCode))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public LaagGeletterdheid getLaagGeletterdheid() {
        int totaalInwoners = 0;
        int totaalAantalLaagGeletterde = 0;

        for (Wijk wijk : wijken) {
            if (wijk.getLaagGeletterdheid() != null) {
                int aantalInwoners = wijk.getAantalInwoners();
                double laagGeletterdheidPercentage = wijk.getLaagGeletterdheid().percentageTaalgroei();

                totaalInwoners += aantalInwoners;
                totaalAantalLaagGeletterde += (int) (aantalInwoners * laagGeletterdheidPercentage);
            }
        }

        double percentageTaalgroei = (double) totaalAantalLaagGeletterde / totaalInwoners;
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        percentageTaalgroei = Double.parseDouble(df.format(percentageTaalgroei));
        return new LaagGeletterdheid(percentageTaalgroei);
    }

    public void voegWijkToe(Wijk wijk) {
        wijken.add(wijk);
    }
}
