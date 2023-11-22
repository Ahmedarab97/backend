package nl.penguins.learnditwin.plaats.domain;

import nl.penguins.learnditwin.plaats.domain.buurtinfo.LaagGeletterdheid;
import org.springframework.data.mongodb.core.mapping.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Document("wijk")
public class Wijk extends Locatie{
    private final String postcode4;
    @DBRef
    private Set<Buurt> buurten;

    public Wijk(String regioCode_id, String naam, String postcode4) {
        super(regioCode_id, naam);
        this.postcode4 = postcode4;
        this.buurten = new HashSet<>();
    }

    @Override
    public int getAantalInwoners() {
        return buurten.stream()
                .mapToInt(Buurt::getAantalInwoners)
                .sum();
    }

    @Override
    public LaagGeletterdheid getLaagGeletterdheid() {
        int totaalInwoners = 0;
        int totaalAantalLaagGeletterde = 0;

        for (Buurt buurt : buurten){
            if (buurt.getLaagGeletterdheid() != null){
                int aantalInwoners = buurt.getAantalInwoners();
                double laagGeletterdheidPercentage = buurt.getLaagGeletterdheid().percentageTaalgroei();

                totaalInwoners += aantalInwoners;
                totaalAantalLaagGeletterde += (int) (aantalInwoners * laagGeletterdheidPercentage);
            }
        }

        double percentageTaalgroei = (double) totaalAantalLaagGeletterde / totaalInwoners;
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        percentageTaalgroei = Double.parseDouble(df.format(percentageTaalgroei));
        return new LaagGeletterdheid(percentageTaalgroei);
    }
    public void addBuurt(Buurt buurt) {
        this.buurten.add(buurt);
    }

    public String getPostcode4() {
        return postcode4;
    }

    public Set<Buurt> getBuurten() {
        return buurten;
    }
}
