package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;

import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Locatie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlleCijfersGezondheidMonitorConverter implements DataConverter {
    private final ExcelHandelaar excelHandelaar;
    private final GemeenteRepository gemeenteRepository;

    public AlleCijfersGezondheidMonitorConverter(ExcelHandelaar excelHandelaar, GemeenteRepository gemeenteRepository) {
        this.excelHandelaar = excelHandelaar;
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> dataRegels = excelHandelaar.readData(path, 1,2);

        for (String[] regel : dataRegels) {
            String regioCode = regel[0];
            String regioNaam = regel[1];
            String soortRegio = regel[2];

            try {
                Gemeente gemeente = gemeenteRepository.findGemeenteByCode(regioCode);
                Locatie locatie = gemeente.getLocatieByRegioCode(regioCode);

                leesEnSetLocatieInfo(regel, locatie);

                gemeenteRepository.save(gemeente);
            } catch (Exception e){
                // todo specifieke error voor findGemeenteByCode
                continue;
            }
        }
    }

    private static void leesEnSetLocatieInfo(String[] regel, Locatie locatie) {
        double voldoetAanAlcoholRichtlijn = Double.parseDouble(regel[3]);
        double drinker = Double.parseDouble(regel[4]);
        double zwareDrinker = Double.parseDouble(regel[5]);
        double overMatigeDrinker = Double.parseDouble(regel[6]);
        // HIER EINDIGT ALCOHOL GEBRUIK

        // HIER BEGINT GEWICHT
        double onderGewicht = Double.parseDouble(regel[7]);
        double normaalGewicht = Double.parseDouble(regel[8]);
        double overgewicht = Double.parseDouble(regel[9]);
        double ernstigOvergewicht = Double.parseDouble(regel[10]);
        // HIER EINDIGT GEWICHT

        double roker = Double.parseDouble(regel[11]);

        // VANAF HIER SPORT DATA :D
        double voldoetAanBeweegRichtlijn = Double.parseDouble(regel[12]);
        double wekelijkseSporter = Double.parseDouble(regel[13]);

        // HIER EINDIGT SPORT DATA:D
        double goedErvarenGezondheid = Double.parseDouble(regel[17]);

        double langdurigeAandoeningen = Double.parseDouble(regel[18]);
        double beperktVanwegeGezondheid = Double.parseDouble(regel[19]);
        double ernstigBeperktVanwegeGezondheid = Double.parseDouble(regel[20]);
        double langdurigeZiekteEnBeperkt = Double.parseDouble(regel[21]);
        double gehoorBeperking = Double.parseDouble(regel[22]);
        double gezichtBeperking = Double.parseDouble(regel[23]);
        double mobiliteitBeperking = Double.parseDouble(regel[24]);
        double eenOfMeerLichamelijkeBeperking = Double.parseDouble(regel[25]);

        double matigOfHoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[26]);
        double hoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[27]);
        double veelStressInAfgelopen4Weken = Double.parseDouble(regel[28]);
        double eenzaam = Double.parseDouble(regel[29]);
        double ernstigOfZeerEenzaam = Double.parseDouble(regel[30]);
        double emotioneelEenzaam = Double.parseDouble(regel[31]);
        double sociaalEenzaam = Double.parseDouble(regel[32]);

        double matigOfVeelRegieOverEigenLeven = Double.parseDouble(regel[33]);

        double moeiteMetRondkomen = Double.parseDouble(regel[34]);

        double ernstigGeluidhinderDoorBuren = Double.parseDouble(regel[35]);

        double vrijwilligersWerk = Double.parseDouble(regel[36]);
        double mantelZorger = Double.parseDouble(regel[37]);

        locatie.getLocatieInfo().setAlcoholGebruik(voldoetAanAlcoholRichtlijn, drinker, zwareDrinker, overMatigeDrinker);
        locatie.getLocatieInfo().setGewicht(onderGewicht, normaalGewicht, overgewicht, ernstigOvergewicht);
        locatie.getLocatieInfo().setRoken(roker);
        locatie.getLocatieInfo().setBewegen(voldoetAanBeweegRichtlijn, wekelijkseSporter);
        locatie.getLocatieInfo().setGezondheid(goedErvarenGezondheid);
        locatie.getLocatieInfo().setLichamelijkeBeperkingOfAandoening(langdurigeAandoeningen, beperktVanwegeGezondheid, ernstigBeperktVanwegeGezondheid, langdurigeZiekteEnBeperkt, gehoorBeperking, gezichtBeperking, mobiliteitBeperking, eenOfMeerLichamelijkeBeperking);
        locatie.getLocatieInfo().setMentaleProblemen(matigOfHoogRisicoOpAngstOfDepressie, hoogRisicoOpAngstOfDepressie, veelStressInAfgelopen4Weken, eenzaam, ernstigOfZeerEenzaam, emotioneelEenzaam, sociaalEenzaam);
        locatie.getLocatieInfo().setRegieOverEigenLeven(matigOfVeelRegieOverEigenLeven);
        locatie.getLocatieInfo().setFinancieel(moeiteMetRondkomen);
        locatie.getLocatieInfo().setGeluidshinder(ernstigGeluidhinderDoorBuren);
        locatie.getLocatieInfo().setOndersteuning(vrijwilligersWerk, mantelZorger);
    }
}
