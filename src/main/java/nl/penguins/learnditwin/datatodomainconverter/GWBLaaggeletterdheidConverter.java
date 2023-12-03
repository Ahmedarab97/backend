package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.dataconverterhelpers.DataConverterHelper;
import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import org.springframework.stereotype.Component;

import java.text.DecimalFormatSymbols;
import java.util.Locale;


import java.text.DecimalFormat;
import java.util.List;

@Component
public class GWBLaaggeletterdheidConverter implements DataConverter {
    private final ExcelHandelaar fileHandler;
    private final GemeenteRepository gemeenteRepository;

    public GWBLaaggeletterdheidConverter(ExcelHandelaar fileHandler, GemeenteRepository gemeenteRepository) {
        this.fileHandler = fileHandler;
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> laaggeletterdheidData = fileHandler.readData(path, 1, 12, 0);
        List<String[]> wijkInfoSheet = fileHandler.readData(path, 1, 1);

        String[] wijkInfo = wijkInfoSheet.get(0);

        String gemeenteNaam = wijkInfo[1];
        String wijkNaam = wijkInfo[2];
        String buurtNaam = wijkInfo[3];

        Gemeente gemeente = gemeenteRepository.findByNaam(gemeenteNaam).orElseThrow();
        Buurt buurt = gemeente.getWijkByWijkNaam(wijkNaam)
                .getBuurtByNaam(buurtNaam);

        int totaalAantalHuishoudensTaalgroei = 0;
        int totaalAantalHuishoudens = 0;

        for (String[] line : laaggeletterdheidData) {
            int NT1Taalgroeiers = Integer.parseInt((line[1]).split("\\.")[0]);
            totaalAantalHuishoudensTaalgroei += NT1Taalgroeiers;


            int base = Integer.parseInt((line[3]).split("\\.")[0]);
            totaalAantalHuishoudens += base;
        }

        double percentageTaalgroeiAfgerond = DataConverterHelper.naarPercentage(totaalAantalHuishoudens, totaalAantalHuishoudensTaalgroei);

        buurt.getLocatieInfo().setLaagGeletterdheid(percentageTaalgroeiAfgerond);
        gemeenteRepository.save(gemeente);
    }
}
