package nl.penguins.learnditwin.plaats.domain.buurtinfo;

import lombok.Getter;

@Getter
public class LocatieInfo {
    private AlcoholGebruik alcoholGebruik;
    private Bewegen bewegen;
    private Financieel financieel;
    private Geluidshinder geluidshinder;
    private Gewicht gewicht;
    private LichamelijkeBeperkingOfAandoening lichamelijkeBeperkingOfAandoening;
    private MentaleProblemen mentaleProblemen;
    private Ondersteuning ondersteuning;
    private RegieOverEigenLeven regieOverEigenLeven;
    private Roken roken;
    private Gezondheid gezondheid;
    private LaagGeletterdheid laagGeletterdheid;
    private Leeftijd leeftijd;
    private Huishouden huishouden;
    private BevolkingsDichtheid bevolkingsDichtheid;

    public LocatieInfo() {
        this.leeftijd = new Leeftijd();
    }

    public void setAlcoholGebruik(double percentageVoldoetAanAlcoholRichtlijn,
                                  double percentageDrinker,
                                  double percentageZwareDrinker,
                                  double percentageOvermatigeDrinker){
        this.alcoholGebruik = new AlcoholGebruik(percentageVoldoetAanAlcoholRichtlijn, percentageDrinker, percentageZwareDrinker, percentageOvermatigeDrinker);
    }

    public void setBewegen(double percentageVoldoetAanBeweegRichtlijn,
                           double percentageWekelijkseSporter){
        this.bewegen = new Bewegen(percentageVoldoetAanBeweegRichtlijn, percentageWekelijkseSporter);
    }

    public void setFinancieel(double percentageMoeiteMetRondkomen){
        this.financieel = new Financieel(percentageMoeiteMetRondkomen);
    }

    public void setGeluidshinder(double percentageErnstigeGeluidhinderDoorBuren){
        this.geluidshinder = new Geluidshinder(percentageErnstigeGeluidhinderDoorBuren);
    }

    public void setGewicht(double percentageOndergewicht,
                           double percentageNormaalGewicht,
                           double percentageOvergewicht,
                           double percentageErnstigOvergewicht){
        this.gewicht = new Gewicht(percentageOndergewicht, percentageNormaalGewicht, percentageOvergewicht, percentageErnstigOvergewicht);
    }

    public void setGezondheid(double percentageGoedOfZeerErvarenGezondheid){
        this.gezondheid = new Gezondheid(percentageGoedOfZeerErvarenGezondheid);
    }

    public void setLichamelijkeBeperkingOfAandoening(double percentageEenOfMeerLangdurigeAandoeningen,
                                                     double percentageBeperktVanwegeGezondheid,
                                                     double percentageErnstigBeperktVanwegeGezondheid,
                                                     double percentageLangdurigeZiekteEnBeperkt,
                                                     double percentageGehoorBeperking,
                                                     double percentageGezichtBeperking,
                                                     double percentageMobiliteitBeperking,
                                                     double percentageEenOfMeerLichamelijkeBeperkingen){
        this.lichamelijkeBeperkingOfAandoening = new LichamelijkeBeperkingOfAandoening(percentageEenOfMeerLangdurigeAandoeningen, percentageBeperktVanwegeGezondheid, percentageErnstigBeperktVanwegeGezondheid, percentageLangdurigeZiekteEnBeperkt, percentageGehoorBeperking, percentageGezichtBeperking, percentageMobiliteitBeperking, percentageEenOfMeerLichamelijkeBeperkingen);
    }

    public void setMentaleProblemen(double percentageMatigOfHoogRisicoOpAngstOfDepressie,
                                    double percentageHoogRisicoOpAngstOfDepressie,
                                    double percentageVeelStressInAfgelopen4Weken,
                                    double percentageEenzaam,
                                    double percentageErnstigOfZeerEenzaam,
                                    double percentageEmotioneelEenzaam,
                                    double percentageSociaalEenzaam){
        this.mentaleProblemen = new MentaleProblemen(percentageMatigOfHoogRisicoOpAngstOfDepressie, percentageHoogRisicoOpAngstOfDepressie, percentageVeelStressInAfgelopen4Weken, percentageEenzaam, percentageErnstigOfZeerEenzaam, percentageEmotioneelEenzaam, percentageSociaalEenzaam);
    }

    public void setOndersteuning(double percentageVrijwilligersWerk,
                                 double percentageMantelZorger){
        this.ondersteuning = new Ondersteuning(percentageVrijwilligersWerk, percentageMantelZorger);
    }

    public void setRegieOverEigenLeven(double percentageMatigOfVeelRegieOverEigenLeven){
        this.regieOverEigenLeven = new RegieOverEigenLeven(percentageMatigOfVeelRegieOverEigenLeven);
    }

    public void setRoken(double percentageRokers) {
        this.roken = new Roken(percentageRokers);
    }

    public void setLaagGeletterdheid(double laagGeletterdheid){
        this.laagGeletterdheid = new LaagGeletterdheid(laagGeletterdheid);
    }

    public void setHuishouden(double percentage1PersoonsHuishoudens){
        this.huishouden = new Huishouden(percentage1PersoonsHuishoudens);
    }

    public void setBevolkingsDichtheid(int bevolkingsDichtheidPerKilometer2) {
        this.bevolkingsDichtheid = new BevolkingsDichtheid(bevolkingsDichtheidPerKilometer2);
    }
}
