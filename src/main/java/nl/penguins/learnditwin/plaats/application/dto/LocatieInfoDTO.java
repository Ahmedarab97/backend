package nl.penguins.learnditwin.plaats.application.dto;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.Locatie;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.*;

@Getter
public class LocatieInfoDTO {
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
    private final Huishouden huishouden;
    private final Leeftijd leeftijd;

    public LocatieInfoDTO(Locatie locatie) {
        this.alcoholGebruik = locatie.getLocatieInfo().getAlcoholGebruik();
        this.bewegen = locatie.getLocatieInfo().getBewegen();
        this.financieel = locatie.getLocatieInfo().getFinancieel();
        this.geluidshinder = locatie.getLocatieInfo().getGeluidshinder();
        this.gewicht = locatie.getLocatieInfo().getGewicht();
        this.lichamelijkeBeperkingOfAandoening = locatie.getLocatieInfo().getLichamelijkeBeperkingOfAandoening();
        this.mentaleProblemen = locatie.getLocatieInfo().getMentaleProblemen();
        this.ondersteuning = locatie.getLocatieInfo().getOndersteuning();
        this.regieOverEigenLeven = locatie.getLocatieInfo().getRegieOverEigenLeven();
        this.roken = locatie.getLocatieInfo().getRoken();
        this.gezondheid = locatie.getLocatieInfo().getGezondheid();
        this.laagGeletterdheid = locatie.getLaagGeletterdheid();
        this.huishouden = locatie.getLocatieInfo().getHuishouden();
        this.leeftijd = locatie.getLocatieInfo().getLeeftijd();
    }
}
