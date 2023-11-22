package nl.penguins.learnditwin.plaats.domain;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.LaagGeletterdheid;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Getter
@Document("gemeente")
public class Gemeente extends Locatie {
    @DBRef
    private Set<Wijk> wijken;

    public Gemeente(String regioCode_id, String naam) {
        super(regioCode_id, naam);
        this.wijken = new HashSet<>();
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
