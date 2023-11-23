package nl.penguins.learnditwin.plaats.domain;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.LaagGeletterdheid;
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
    public Gemeente(String regioCode_id, String naam) {
        super(regioCode_id, naam);
        this.wijken = new HashSet<>();
    }

    public Wijk getWijkByWijkNaam(String wijkNaam){
        return wijken.stream()
                .filter(wijk -> wijk.getNaam().equals(wijkNaam))
                .findFirst()
                .orElseThrow();
    }

    public Locatie getLocatieByRegioCode(String regioCode){
        String soortLocatie = regioCode.substring(0, 2);

        if (soortLocatie.equals("GM")){
            return this;
        }

        String wijkCode = "WK" + regioCode.substring(2, 8);

        if (soortLocatie.equals("WK")){
            return getWijkByWijkId(wijkCode);
        } else if (soortLocatie.equals("BU")){
            String buurtCode = "BU" + regioCode.substring(2, 10);
            return getWijkByWijkId(wijkCode).getBuurtByRegioCode(buurtCode);
        }

        throw new RuntimeException("Locatie type niet herkend");
    }

    private Wijk getWijkByWijkId(String wijkCode){
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
