package nl.penguins.learnditwin.plaats.domain.buurtinfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Financieel {
    private double percentageMoeiteMetRondkomen;
    private double percentageBijstand;

    public Financieel() {
    }
}