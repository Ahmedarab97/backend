package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlleCijfersGezondheidMonitorConverter implements DataConverter{
    ExcelHandelaar excelHandelaar;
    @Autowired
    BuurtRepository buurtRepository;
    public AlleCijfersGezondheidMonitorConverter(ExcelHandelaar excelHandelaar) {
        this.excelHandelaar = excelHandelaar;
    }

    @Override
    public void convertData(String path) {
        List<String[]> dataRegels = excelHandelaar.readData(path, 2);

        for (String[] regel : dataRegels){
            String regioCode = regel[0];
            String regioNaam = regel[1];
            String soortRegio = regel[2];

            // TODO: we kunnen er nog voor kiezen om dit ook op te slaan op Gemeente en Wijk niveau -> voor verband
            if(soortRegio.equals("Buurt")){
                Buurt buurt = buurtRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("Buurt niet gevonden"));
                // HIER BEGINT ALCOHOL GEBRUIK
                double voldoetAanAlcoholRichtlijn = Double.parseDouble(regel[3]);
                double drinker = Double.parseDouble(regel[4]);
                double zwareDrinker = Double.parseDouble(regel[5]);
                double overMatigeDrinker = Double.parseDouble(regel[6]);
                buurt.setAlcoholGebruik(voldoetAanAlcoholRichtlijn, drinker, zwareDrinker, overMatigeDrinker);
                // HIER EINDIGT ALCOHOL GEBRUIK

                // HIER BEGINT GEWICHT
                double onderGewicht = Double.parseDouble(regel[7]);
                double normaalGewicht = Double.parseDouble(regel[8]);
                double overgewicht = Double.parseDouble(regel[9]);
                double ernstigOvergewicht = Double.parseDouble(regel[10]);
                buurt.setGewicht(onderGewicht, normaalGewicht, overgewicht, ernstigOvergewicht);
                // HIER EINDIGT GEWICHT

                double roker = Double.parseDouble(regel[11]);
                buurt.setRoken(roker);

                // VANAF HIER SPORT DATA :D
                double voldoetAanBeweegRichtlijn = Double.parseDouble(regel[12]);
                double wekelijkseSporter = Double.parseDouble(regel[13]);

                buurt.setBewegen(voldoetAanBeweegRichtlijn, wekelijkseSporter);
                // HIER EINDIGT SPORT DATA:D

                double goedErvarenGezondheid = Double.parseDouble(regel[14]);
                buurt.setGezondheid(goedErvarenGezondheid);

                double langdurigeAandoeningen = Double.parseDouble(regel[15]);
                double beperktVanwegeGezondheid = Double.parseDouble(regel[16]);
                double ernstigBeperktVanwegeGezondheid = Double.parseDouble(regel[17]);
                double langdurigeZiekteEnBeperkt = Double.parseDouble(regel[18]);
                double gehoorBeperking = Double.parseDouble(regel[19]);
                double gezichtBeperking = Double.parseDouble(regel[20]);
                double mobiliteitBeperking = Double.parseDouble(regel[21]);
                double eenOfMeerLichamelijkeBeperking = Double.parseDouble(regel[22]);
                buurt.setLichamelijkeBeperkingOfAandoening(langdurigeAandoeningen, beperktVanwegeGezondheid, ernstigBeperktVanwegeGezondheid, langdurigeZiekteEnBeperkt, gehoorBeperking, gezichtBeperking, mobiliteitBeperking, eenOfMeerLichamelijkeBeperking);

                double matigOfHoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[23]);
                double hoogRisicoOpAngstOfDepressie = Double.parseDouble(regel[24]);
                double veelStressInAfgelopen4Weken = Double.parseDouble(regel[25]);
                double eenzaam = Double.parseDouble(regel[26]);
                double ernstigOfZeerEenzaam = Double.parseDouble(regel[27]);
                double emotioneelEenzaam = Double.parseDouble(regel[28]);
                double sociaalEenzaam = Double.parseDouble(regel[29]);
                buurt.setMentaleProblemen(matigOfHoogRisicoOpAngstOfDepressie, hoogRisicoOpAngstOfDepressie, veelStressInAfgelopen4Weken, eenzaam, ernstigOfZeerEenzaam, emotioneelEenzaam, sociaalEenzaam);

                double matigOfVeelRegieOverEigenLeven = Double.parseDouble(regel[30]);
                buurt.setRegieOverEigenLeven(matigOfVeelRegieOverEigenLeven);

                double moeiteMetRondkomen = Double.parseDouble(regel[31]);
                buurt.setFinancieel(moeiteMetRondkomen);

                double ernstigGeluidhinderDoorBuren = Double.parseDouble(regel[32]);
                buurt.setGeluidshinder(ernstigGeluidhinderDoorBuren);

                double vrijwilligersWerk = Double.parseDouble(regel[33]);
                double mantelZorger = Double.parseDouble(regel[34]);
                buurt.setOndersteuning(vrijwilligersWerk, mantelZorger);

                buurtRepository.save(buurt);
            }

        }

    }
}
