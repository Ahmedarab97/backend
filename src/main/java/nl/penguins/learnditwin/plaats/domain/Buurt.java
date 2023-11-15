package nl.penguins.learnditwin.plaats.domain;

import nl.penguins.learnditwin.plaats.domain.buurtinfo.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Buurt {
    @MongoId(FieldType.STRING)
    @Field("buurtCode_id")
    @Indexed(unique = true)
    private String buurtCode_id;
    private String naam;
    private int aantalHuishoudens;
    private List<String> postcode6 = new ArrayList<>();
    private AlcoholGebruik alcoholGebruik;
    private Bewegen bewegen;
    private LaagGeletterdheid laagGeletterdheid;
    private Financieel financieel;
    private Geluidshinder geluidshinder;
    private Gewicht gewicht;
    private LichamelijkeBeperkingOfAandoening lichamelijkeBeperkingOfAandoening;
    private MentaleProblemen mentaleProblemen;
    private Ondersteuning ondersteuning;
    private RegieOverEigenLeven regieOverEigenLeven;
    private Roken roken;
    private Gezondheid gezondheid;

    protected Buurt() {
    }

    public Buurt(String naam, int aantalHuishoudens, List<String> postcode6) {
        this.naam = naam;
        this.aantalHuishoudens = aantalHuishoudens;
        this.postcode6 = postcode6;
    }

    public Buurt(String buurtCode_id, String naam) {
        this.buurtCode_id = buurtCode_id;
        this.naam = naam;
    }

    public void addStraat(String postcode6) {
        this.postcode6.add(postcode6);
    }

    public void setLaagGeletterdheid(LaagGeletterdheid laagGeletterdheid){
        this.laagGeletterdheid = laagGeletterdheid;
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

    public void setRoken(double percentageRokers){
        this.roken = new Roken(percentageRokers);
    }

    public void setAantalHuishoudens(int aantalHuishoudens) {
        this.aantalHuishoudens = aantalHuishoudens;
    }

    public int getAantalHuishoudens() {
        return aantalHuishoudens;
    }

    public String getNaam() {
        return naam;
    }

    public String getBuurtCode_id() {
        return buurtCode_id;
    }

    public List<String> getPostcode6() {
        return postcode6;
    }

    public LaagGeletterdheid getLaagGeletterdheid() {
        return laagGeletterdheid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buurt buurt = (Buurt) o;
        return Objects.equals(buurtCode_id, buurt.buurtCode_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buurtCode_id);
    }
}