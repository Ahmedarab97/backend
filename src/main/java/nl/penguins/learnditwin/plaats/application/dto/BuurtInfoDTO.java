package nl.penguins.learnditwin.plaats.application.dto;

import nl.penguins.learnditwin.plaats.domain.Locatie;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.*;

public class BuurtInfoDTO {
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

    public BuurtInfoDTO(Locatie locatie) {
        this.alcoholGebruik = locatie.getAlcoholGebruik();
        this.bewegen = locatie.getBewegen();
        this.financieel = locatie.getFinancieel();
        this.geluidshinder = locatie.getGeluidshinder();
        this.gewicht = locatie.getGewicht();
        this.lichamelijkeBeperkingOfAandoening = locatie.getLichamelijkeBeperkingOfAandoening();
        this.mentaleProblemen = locatie.getMentaleProblemen();
        this.ondersteuning = locatie.getOndersteuning();
        this.regieOverEigenLeven = locatie.getRegieOverEigenLeven();
        this.roken = locatie.getRoken();
        this.gezondheid = locatie.getGezondheid();
        this.laagGeletterdheid = locatie.getLaagGeletterdheid();
    }

    public BuurtInfoDTO(AlcoholGebruik alcoholGebruik, Bewegen bewegen, Financieel financieel, Geluidshinder geluidshinder, Gewicht gewicht, LichamelijkeBeperkingOfAandoening lichamelijkeBeperkingOfAandoening, MentaleProblemen mentaleProblemen, Ondersteuning ondersteuning, RegieOverEigenLeven regieOverEigenLeven, Roken roken, Gezondheid gezondheid, LaagGeletterdheid laagGeletterdheid) {
        this.alcoholGebruik = alcoholGebruik;
        this.bewegen = bewegen;
        this.financieel = financieel;
        this.geluidshinder = geluidshinder;
        this.gewicht = gewicht;
        this.lichamelijkeBeperkingOfAandoening = lichamelijkeBeperkingOfAandoening;
        this.mentaleProblemen = mentaleProblemen;
        this.ondersteuning = ondersteuning;
        this.regieOverEigenLeven = regieOverEigenLeven;
        this.roken = roken;
        this.gezondheid = gezondheid;
        this.laagGeletterdheid = laagGeletterdheid;
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
