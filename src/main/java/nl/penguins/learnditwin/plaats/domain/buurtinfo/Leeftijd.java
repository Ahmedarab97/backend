package nl.penguins.learnditwin.plaats.domain.buurtinfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Leeftijd {
    private double percentageJongerDan15;
    private double percentageOuderDan65;

    public Leeftijd() {
    }
}
