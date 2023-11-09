package nl.penguins.learnditwin.dataconverter;

import nl.penguins.learnditwin.dataconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.data.PlaatsRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.LaagGeletterdheid;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GWBLaaggeletterdheidConverter implements DataConverter{
    private final ExcelHandelaar fileHandler;
    private final BuurtRepository buurtRepository;
    private final PlaatsRepository plaatsRepository;

    public GWBLaaggeletterdheidConverter(ExcelHandelaar fileHandler, BuurtRepository buurtRepository, PlaatsRepository plaatsRepository) {
        this.fileHandler = fileHandler;
        this.buurtRepository = buurtRepository;
        this.plaatsRepository = plaatsRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> laaggeletterdheidData = fileHandler.readData(path, 0);
        List<String[]> wijkInfoSheet = fileHandler.readData(path, 1);
        // aantal huishoudens
        // aantal huishoudens met taalgroei
        // todo: hoe slaan we deze data op gekoppeld aan een buurt neem ik aan?

        String[] wijkInfo = wijkInfoSheet.get(0);

        String provincie = wijkInfo[0];
        String gemeente = wijkInfo[1];
        String wijk = wijkInfo[2];
        String buurtNaam = wijkInfo[3];

        System.out.println(buurtNaam);
        System.out.println(wijk);
        // todo dit geeft meerdere buurten met dezelfde naam. Misschien zoeken vanuit een wijk met een bepaallde naam, naar een buurt
        Buurt buurt = plaatsRepository.findBuurtByWijkAndBuurtNaam(wijk, buurtNaam).orElseThrow(() -> new RuntimeException("Buurt niet gevonden"));

        int totaalAantalHuishoudensTaalgroei = 0;
        int totaalAantalHuishoudens = 0;

        for (int i = 0; i < 11 ; i++){
            String[] line = laaggeletterdheidData.get(i);

            String whizeSegmant = line[0];

            int NT1Taalgroeiers = Integer.parseInt((line[1]).split("\\.")[0]);
            totaalAantalHuishoudensTaalgroei += NT1Taalgroeiers;

            double percentageNT1Taalgroeiers = Double.parseDouble(line[2].replace(",", "."));

            int base = Integer.parseInt((line[3]).split("\\.")[0]);
            totaalAantalHuishoudens += base;

            double basePercentage = Double.parseDouble(line[4].replace(",", "."));
            double index = Double.parseDouble(line[5].replace(",", "."));
            double percentagePenetratie = Double.parseDouble(line[6].replace(",", "."));
        }

        LaagGeletterdheid geletterdheid = new LaagGeletterdheid(totaalAantalHuishoudens, totaalAantalHuishoudensTaalgroei);

        buurt.setLaagGeletterdheid(geletterdheid);
        buurtRepository.save(buurt);
    }
}
