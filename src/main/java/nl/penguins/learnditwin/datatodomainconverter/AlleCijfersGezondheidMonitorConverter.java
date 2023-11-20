package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;

import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.data.WijkRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlleCijfersGezondheidMonitorConverter implements DataConverter {
    private final ExcelHandelaar excelHandelaar;
    private final BuurtRepository buurtRepository;
    private final GemeenteRepository gemeenteRepository;
    private final WijkRepository wijkRepository;

    public AlleCijfersGezondheidMonitorConverter(ExcelHandelaar excelHandelaar, BuurtRepository buurtRepository, GemeenteRepository gemeenteRepository, WijkRepository wijkRepository) {
        this.excelHandelaar = excelHandelaar;
        this.buurtRepository = buurtRepository;
        this.gemeenteRepository = gemeenteRepository;
        this.wijkRepository = wijkRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> dataRegels = excelHandelaar.readData(path, 2);

        for (String[] regel : dataRegels) {
            String regioCode = regel[0];
            String regioNaam = regel[1];
            String soortRegio = regel[2];

            switch (soortRegio) {
                case "Gemeente" -> {
                    Gemeente locatie = gemeenteRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    // HIER BEGINT ALCOHOL GEBRUIK
                    double voldoetAanAlcoholRichtlijn = Double.parseDouble(regel[3]);
                    double drinker = Double.parseDouble(regel[4]);
                    double zwareDrinker = Double.parseDouble(regel[5]);
                    double overMatigeDrinker = Double.parseDouble(regel[6]);
                    locatie.setAlcoholGebruik(voldoetAanAlcoholRichtlijn, drinker, zwareDrinker, overMatigeDrinker);
                    // HIER EINDIGT ALCOHOL GEBRUIK

                    // HIER BEGINT GEWICHT
                    double onderGewicht = Double.parseDouble(regel[7]);
                    double normaalGewicht = Double.parseDouble(regel[8]);
                    double overgewicht = Double.parseDouble(regel[9]);
                    double ernstigOvergewicht = Double.parseDouble(regel[10]);
                    locatie.setGewicht(onderGewicht, normaalGewicht, overgewicht, ernstigOvergewicht);
                    // HIER EINDIGT GEWICHT

                    double roker = Double.parseDouble(regel[11]);
                    locatie.setRoken(roker);

                    // VANAF HIER SPORT DATA :D
                    double voldoetAanBeweegRichtlijn = Double.parseDouble(regel[12]);
                    double wekelijkseSporter = Double.parseDouble(regel[13]);

                    locatie.setBewegen(voldoetAanBeweegRichtlijn, wekelijkseSporter);
                    // HIER EINDIGT SPORT DATA:D

                    double goedErvarenGezondheid = Double.parseDouble(regel[14]);
                    locatie.setGezondheid(goedErvarenGezondheid);

                    double langdurigeAandoeningen = Double.parseDouble(regel[15]);
                    double beperktVanwegeGezondheid = Double.parseDouble(regel[16]);
                    double ernstigBeperktVanwegeGezondheid = Double.parseDouble(regel[17]);
                    double langdurigeZiekteEnBeperkt = Double.parseDouble(regel[18]);
                    double gehoorBeperking = Double.parseDouble(regel[19]);
                    double gezichtBeperking = Double.parseDouble(regel[20]);
                    double mobiliteitBeperking = Double.parseDouble(regel[21]);
                    double eenOfMeerLichamelijkeBeperking = Double.parseDouble(regel[22]);
                    locatie.setLichamelijkeBeperkingOfAandoening(langdurigeAandoeningen, beperktVanwegeGezondheid, ernstigBeperktVanwegeGezondheid, langdurigeZiekteEnBeperkt, gehoorBeperking, gezichtBeperking, mobiliteitBeperking, eenOfMeerLichamelijkeBeperking);

                    double matigOfHoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[23]);
                    double hoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[24]);
                    double veelStressInAfgelopen4Weken = Double.parseDouble(regel[25]);
                    double eenzaam = Double.parseDouble(regel[26]);
                    double ernstigOfZeerEenzaam = Double.parseDouble(regel[27]);
                    double emotioneelEenzaam = Double.parseDouble(regel[28]);
                    double sociaalEenzaam = Double.parseDouble(regel[29]);
                    locatie.setMentaleProblemen(matigOfHoogRisicoOpAngstOfDepressie, hoogRisicoOpAngstOfDepressie, veelStressInAfgelopen4Weken, eenzaam, ernstigOfZeerEenzaam, emotioneelEenzaam, sociaalEenzaam);

                    double matigOfVeelRegieOverEigenLeven = Double.parseDouble(regel[30]);
                    locatie.setRegieOverEigenLeven(matigOfVeelRegieOverEigenLeven);

                    double moeiteMetRondkomen = Double.parseDouble(regel[31]);
                    locatie.setFinancieel(moeiteMetRondkomen);

                    double ernstigGeluidhinderDoorBuren = Double.parseDouble(regel[32]);
                    locatie.setGeluidshinder(ernstigGeluidhinderDoorBuren);

                    double vrijwilligersWerk = Double.parseDouble(regel[33]);
                    double mantelZorger = Double.parseDouble(regel[34]);
                    locatie.setOndersteuning(vrijwilligersWerk, mantelZorger);

                    gemeenteRepository.save(locatie);
                }
                case "Wijk" -> {
                    Wijk locatie = wijkRepository.findById(regioCode).orElseThrow();

                    // HIER BEGINT ALCOHOL GEBRUIK
                    double voldoetAanAlcoholRichtlijn = Double.parseDouble(regel[3]);
                    double drinker = Double.parseDouble(regel[4]);
                    double zwareDrinker = Double.parseDouble(regel[5]);
                    double overMatigeDrinker = Double.parseDouble(regel[6]);
                    locatie.setAlcoholGebruik(voldoetAanAlcoholRichtlijn, drinker, zwareDrinker, overMatigeDrinker);
                    // HIER EINDIGT ALCOHOL GEBRUIK

                    // HIER BEGINT GEWICHT
                    double onderGewicht = Double.parseDouble(regel[7]);
                    double normaalGewicht = Double.parseDouble(regel[8]);
                    double overgewicht = Double.parseDouble(regel[9]);
                    double ernstigOvergewicht = Double.parseDouble(regel[10]);
                    locatie.setGewicht(onderGewicht, normaalGewicht, overgewicht, ernstigOvergewicht);
                    // HIER EINDIGT GEWICHT

                    double roker = Double.parseDouble(regel[11]);
                    locatie.setRoken(roker);

                    // VANAF HIER SPORT DATA :D
                    double voldoetAanBeweegRichtlijn = Double.parseDouble(regel[12]);
                    double wekelijkseSporter = Double.parseDouble(regel[13]);

                    locatie.setBewegen(voldoetAanBeweegRichtlijn, wekelijkseSporter);
                    // HIER EINDIGT SPORT DATA:D

                    double goedErvarenGezondheid = Double.parseDouble(regel[14]);
                    locatie.setGezondheid(goedErvarenGezondheid);

                    double langdurigeAandoeningen = Double.parseDouble(regel[15]);
                    double beperktVanwegeGezondheid = Double.parseDouble(regel[16]);
                    double ernstigBeperktVanwegeGezondheid = Double.parseDouble(regel[17]);
                    double langdurigeZiekteEnBeperkt = Double.parseDouble(regel[18]);
                    double gehoorBeperking = Double.parseDouble(regel[19]);
                    double gezichtBeperking = Double.parseDouble(regel[20]);
                    double mobiliteitBeperking = Double.parseDouble(regel[21]);
                    double eenOfMeerLichamelijkeBeperking = Double.parseDouble(regel[22]);
                    locatie.setLichamelijkeBeperkingOfAandoening(langdurigeAandoeningen, beperktVanwegeGezondheid, ernstigBeperktVanwegeGezondheid, langdurigeZiekteEnBeperkt, gehoorBeperking, gezichtBeperking, mobiliteitBeperking, eenOfMeerLichamelijkeBeperking);

                    double matigOfHoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[23]);
                    double hoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[24]);
                    double veelStressInAfgelopen4Weken = Double.parseDouble(regel[25]);
                    double eenzaam = Double.parseDouble(regel[26]);
                    double ernstigOfZeerEenzaam = Double.parseDouble(regel[27]);
                    double emotioneelEenzaam = Double.parseDouble(regel[28]);
                    double sociaalEenzaam = Double.parseDouble(regel[29]);
                    locatie.setMentaleProblemen(matigOfHoogRisicoOpAngstOfDepressie, hoogRisicoOpAngstOfDepressie, veelStressInAfgelopen4Weken, eenzaam, ernstigOfZeerEenzaam, emotioneelEenzaam, sociaalEenzaam);

                    double matigOfVeelRegieOverEigenLeven = Double.parseDouble(regel[30]);
                    locatie.setRegieOverEigenLeven(matigOfVeelRegieOverEigenLeven);

                    double moeiteMetRondkomen = Double.parseDouble(regel[31]);
                    locatie.setFinancieel(moeiteMetRondkomen);

                    double ernstigGeluidhinderDoorBuren = Double.parseDouble(regel[32]);
                    locatie.setGeluidshinder(ernstigGeluidhinderDoorBuren);

                    double vrijwilligersWerk = Double.parseDouble(regel[33]);
                    double mantelZorger = Double.parseDouble(regel[34]);
                    locatie.setOndersteuning(vrijwilligersWerk, mantelZorger);

                    wijkRepository.save(locatie);
                }
                case "Buurt" -> {
                    Buurt locatie = buurtRepository.findById(regioCode).orElseThrow();

                    // HIER BEGINT ALCOHOL GEBRUIK
                    double voldoetAanAlcoholRichtlijn = Double.parseDouble(regel[3]);
                    double drinker = Double.parseDouble(regel[4]);
                    double zwareDrinker = Double.parseDouble(regel[5]);
                    double overMatigeDrinker = Double.parseDouble(regel[6]);
                    locatie.setAlcoholGebruik(voldoetAanAlcoholRichtlijn, drinker, zwareDrinker, overMatigeDrinker);
                    // HIER EINDIGT ALCOHOL GEBRUIK

                    // HIER BEGINT GEWICHT
                    double onderGewicht = Double.parseDouble(regel[7]);
                    double normaalGewicht = Double.parseDouble(regel[8]);
                    double overgewicht = Double.parseDouble(regel[9]);
                    double ernstigOvergewicht = Double.parseDouble(regel[10]);
                    locatie.setGewicht(onderGewicht, normaalGewicht, overgewicht, ernstigOvergewicht);
                    // HIER EINDIGT GEWICHT

                    double roker = Double.parseDouble(regel[11]);
                    locatie.setRoken(roker);

                    // VANAF HIER SPORT DATA :D
                    double voldoetAanBeweegRichtlijn = Double.parseDouble(regel[12]);
                    double wekelijkseSporter = Double.parseDouble(regel[13]);

                    locatie.setBewegen(voldoetAanBeweegRichtlijn, wekelijkseSporter);
                    // HIER EINDIGT SPORT DATA:D

                    double goedErvarenGezondheid = Double.parseDouble(regel[14]);
                    locatie.setGezondheid(goedErvarenGezondheid);

                    double langdurigeAandoeningen = Double.parseDouble(regel[15]);
                    double beperktVanwegeGezondheid = Double.parseDouble(regel[16]);
                    double ernstigBeperktVanwegeGezondheid = Double.parseDouble(regel[17]);
                    double langdurigeZiekteEnBeperkt = Double.parseDouble(regel[18]);
                    double gehoorBeperking = Double.parseDouble(regel[19]);
                    double gezichtBeperking = Double.parseDouble(regel[20]);
                    double mobiliteitBeperking = Double.parseDouble(regel[21]);
                    double eenOfMeerLichamelijkeBeperking = Double.parseDouble(regel[22]);
                    locatie.setLichamelijkeBeperkingOfAandoening(langdurigeAandoeningen, beperktVanwegeGezondheid, ernstigBeperktVanwegeGezondheid, langdurigeZiekteEnBeperkt, gehoorBeperking, gezichtBeperking, mobiliteitBeperking, eenOfMeerLichamelijkeBeperking);

                    double matigOfHoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[23]);
                    double hoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[24]);
                    double veelStressInAfgelopen4Weken = Double.parseDouble(regel[25]);
                    double eenzaam = Double.parseDouble(regel[26]);
                    double ernstigOfZeerEenzaam = Double.parseDouble(regel[27]);
                    double emotioneelEenzaam = Double.parseDouble(regel[28]);
                    double sociaalEenzaam = Double.parseDouble(regel[29]);
                    locatie.setMentaleProblemen(matigOfHoogRisicoOpAngstOfDepressie, hoogRisicoOpAngstOfDepressie, veelStressInAfgelopen4Weken, eenzaam, ernstigOfZeerEenzaam, emotioneelEenzaam, sociaalEenzaam);

                    double matigOfVeelRegieOverEigenLeven = Double.parseDouble(regel[30]);
                    locatie.setRegieOverEigenLeven(matigOfVeelRegieOverEigenLeven);

                    double moeiteMetRondkomen = Double.parseDouble(regel[31]);
                    locatie.setFinancieel(moeiteMetRondkomen);

                    double ernstigGeluidhinderDoorBuren = Double.parseDouble(regel[32]);
                    locatie.setGeluidshinder(ernstigGeluidhinderDoorBuren);

                    double vrijwilligersWerk = Double.parseDouble(regel[33]);
                    double mantelZorger = Double.parseDouble(regel[34]);
                    locatie.setOndersteuning(vrijwilligersWerk, mantelZorger);

                    buurtRepository.save(locatie);
                }
                default -> {
                    continue;
                }
            }
        }
    }
}