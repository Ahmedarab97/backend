package nl.penguins.learnditwin.plaats.domain.buurtinfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WoonOmstandigheden {
    private int woningPrijs;
    private double percentageFlatBewoners;
    private int aantalInwonersPerKilometer2;

    public WoonOmstandigheden() {
    }
}
