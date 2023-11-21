package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.data.WijkRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.buurtinfo.LaagGeletterdheid;
import org.springframework.stereotype.Component;

import java.text.DecimalFormatSymbols;
import java.util.Locale;


import java.text.DecimalFormat;
import java.util.List;

@Component
public class GWBLaaggeletterdheidConverter implements DataConverter {
    private final ExcelHandelaar fileHandler;
    private final BuurtRepository buurtRepository;
    private final WijkRepository wijkRepository;

    public GWBLaaggeletterdheidConverter(ExcelHandelaar fileHandler, BuurtRepository buurtRepository, WijkRepository wijkRepository) {
        this.fileHandler = fileHandler;
        this.buurtRepository = buurtRepository;
        this.wijkRepository = wijkRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> laaggeletterdheidData = fileHandler.readData(path, 1, 12, 0);
        List<String[]> wijkInfoSheet = fileHandler.readData(path, 1, 1);

        String[] wijkInfo = wijkInfoSheet.get(0);

        String provincie = wijkInfo[0];
        String gemeente = wijkInfo[1];
        String wijk = wijkInfo[2];
        String buurtNaam = wijkInfo[3];

        Buurt buurt = wijkRepository.findBuurtByWijkAndBuurtNaam(wijk, buurtNaam).orElseThrow(() -> new RuntimeException("Buurt niet gevonden"));

        int totaalAantalHuishoudensTaalgroei = 0;
        int totaalAantalHuishoudens = 0;

        for (String[] line : laaggeletterdheidData) {
            int NT1Taalgroeiers = Integer.parseInt((line[1]).split("\\.")[0]);
            totaalAantalHuishoudensTaalgroei += NT1Taalgroeiers;


            int base = Integer.parseInt((line[3]).split("\\.")[0]);
            totaalAantalHuishoudens += base;

            // Deze informatie is alleen nodig als we de whizeSegmant erin willen verwerken
            String whizeSegmant = line[0];
            double percentageNT1Taalgroeier = Double.parseDouble(line[2].replace(",", "."));
            double basePercentage = Double.parseDouble(line[4].replace(",", "."));
            double index = Double.parseDouble(line[5].replace(",", "."));
            double percentagePenetratie = Double.parseDouble(line[6].replace(",", "."));
        }

        double percentageTaalgroeiAfgerond = (double) totaalAantalHuishoudensTaalgroei / totaalAantalHuishoudens;
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        percentageTaalgroeiAfgerond = Double.parseDouble(df.format(percentageTaalgroeiAfgerond));

        buurt.getLocatieInfo().setLaagGeletterdheid(percentageTaalgroeiAfgerond);
        buurt.setAantalHuishoudens(totaalAantalHuishoudens);
        buurtRepository.save(buurt);
    }
}
