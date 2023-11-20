package nl.penguins.learnditwin.plaats.domain;

import nl.penguins.learnditwin.plaats.domain.buurtinfo.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Objects;

@Document("locatie")
public abstract class Locatie {
    @MongoId(FieldType.STRING)
    @Field("regioCode_id")
    @Indexed(unique = true)
    protected String regioCode_id;
    protected String naam;
    protected AlcoholGebruik alcoholGebruik;
    protected Bewegen bewegen;
    protected Financieel financieel;
    protected Geluidshinder geluidshinder;
    protected Gewicht gewicht;
    protected LichamelijkeBeperkingOfAandoening lichamelijkeBeperkingOfAandoening;
    protected MentaleProblemen mentaleProblemen;
    protected Ondersteuning ondersteuning;
    protected RegieOverEigenLeven regieOverEigenLeven;
    protected Roken roken;
    protected Gezondheid gezondheid;
    protected LaagGeletterdheid laagGeletterdheid;

    public Locatie(String regioCode, String naam) {
        this.regioCode_id = regioCode;
        this.naam = naam;
    }

    protected Locatie() {
    }

    public abstract int getAantalInwoners();

    public String getNaam() {
        return naam;
    }

    public String getRegioCode_id() {
        return regioCode_id;
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

    public AlcoholGebruik getAlcoholGebruik() {
        return alcoholGebruik;
    }

    public Bewegen getBewegen() {
        return bewegen;
    }

    public abstract LaagGeletterdheid getLaagGeletterdheid();

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


    public void setLaagGeletterdheid(LaagGeletterdheid laagGeletterdheid){
        this.laagGeletterdheid = laagGeletterdheid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Locatie locatie = (Locatie) o;

        if (!Objects.equals(regioCode_id, locatie.regioCode_id))
            return false;
        return Objects.equals(naam, locatie.naam);
    }

    @Override
    public int hashCode() {
        int result = regioCode_id != null ? regioCode_id.hashCode() : 0;
        result = 31 * result + (naam != null ? naam.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Locatie{" +
                "regioCode_id='" + regioCode_id + '\'' +
                ", naam='" + naam + '\'' +
                ", alcoholGebruik=" + alcoholGebruik +
                ", bewegen=" + bewegen +
                ", financieel=" + financieel +
                ", geluidshinder=" + geluidshinder +
                ", gewicht=" + gewicht +
                ", lichamelijkeBeperkingOfAandoening=" + lichamelijkeBeperkingOfAandoening +
                ", mentaleProblemen=" + mentaleProblemen +
                ", ondersteuning=" + ondersteuning +
                ", regieOverEigenLeven=" + regieOverEigenLeven +
                ", roken=" + roken +
                ", gezondheid=" + gezondheid +
                '}';
    }
}
