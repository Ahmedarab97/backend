package nl.penguins.learnditwin.layers.application;

public class TijdelijkStad {
    private String naam;
    private int bevolkingAantal;
    private int geletterdheid;

    public TijdelijkStad(String naam, int bevolkingAantal, int geletterdheid) {
        this.naam = naam;
        this.bevolkingAantal = bevolkingAantal;
        this.geletterdheid = geletterdheid;
    }

    public TijdelijkStad() {

    }

    public String getNaam() {
        return naam;
    }

    public int getBevolkingAantal() {
        return bevolkingAantal;
    }

    public int getGeletterdheid() {
        return geletterdheid;
    }
}
