package nl.penguins.learnditwin.plaats.application.dto;

import nl.penguins.learnditwin.plaats.domain.Wijk;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.*;

public class WijkDTO {
    private final String wijkCode;
    private final String naam;
    private final AlcoholGebruik alcoholGebruik;
    private final Bewegen bewegen;
    private final Financieel financieel;
    private final Geluidshinder geluidshinder;
    private final Gewicht gewicht;
    private final LichamelijkeBeperkingOfAandoening lichamelijkeBeperkingOfAandoening;
    private final MentaleProblemen mentaleProblemen;
    private final Ondersteuning ondersteuning;
    private final RegieOverEigenLeven regieOverEigenLeven;
    private final Roken roken;
    private final Gezondheid gezondheid;
    private final LaagGeletterdheid laagGeletterdheid;

    public WijkDTO(Wijk wijk) {
        this.wijkCode = wijk.getRegioCode_id();
        this.naam = wijk.getNaam();
        this.alcoholGebruik = wijk.getAlcoholGebruik();
        this.bewegen = wijk.getBewegen();
        this.financieel = wijk.getFinancieel();
        this.geluidshinder = wijk.getGeluidshinder();
        this.gewicht = wijk.getGewicht();
        this.lichamelijkeBeperkingOfAandoening = wijk.getLichamelijkeBeperkingOfAandoening();
        this.mentaleProblemen = wijk.getMentaleProblemen();
        this.ondersteuning = wijk.getOndersteuning();
        this.regieOverEigenLeven = wijk.getRegieOverEigenLeven();
        this.roken = wijk.getRoken();
        this.gezondheid = wijk.getGezondheid();
        this.laagGeletterdheid = wijk.getLaagGeletterdheid();
    }

    public String getWijkCode() {
        return wijkCode;
    }

    public String getNaam() {
        return naam;
    }

    public AlcoholGebruik getAlcoholGebruik() {
        return alcoholGebruik;
    }

    public Bewegen getBewegen() {
        return bewegen;
    }

    public Financieel getFinancieel() {
        return financieel;
    }

    public Geluidshinder getGeluidshinder() {
        return geluidshinder;
    }

    public Gewicht getGewicht() {
        return gewicht;
    }

    public LichamelijkeBeperkingOfAandoening getLichamelijkeBeperkingOfAandoening() {
        return lichamelijkeBeperkingOfAandoening;
    }

    public MentaleProblemen getMentaleProblemen() {
        return mentaleProblemen;
    }

    public Ondersteuning getOndersteuning() {
        return ondersteuning;
    }

    public RegieOverEigenLeven getRegieOverEigenLeven() {
        return regieOverEigenLeven;
    }

    public Roken getRoken() {
        return roken;
    }

    public Gezondheid getGezondheid() {
        return gezondheid;
    }

    public LaagGeletterdheid getLaagGeletterdheid() {
        return laagGeletterdheid;
    }
}
