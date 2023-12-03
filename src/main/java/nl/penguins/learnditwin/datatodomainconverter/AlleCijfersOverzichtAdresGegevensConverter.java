package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.dataconverterhelpers.DataConverterHelper;
import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Locatie;
import nl.penguins.learnditwin.plaats.domain.ids.RegioCode;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AlleCijfersOverzichtAdresGegevensConverter implements DataConverter{
    private final ExcelHandelaar fileHandler;
    private final GemeenteRepository gemeenteRepository;

    public AlleCijfersOverzichtAdresGegevensConverter(ExcelHandelaar fileHandler, GemeenteRepository gemeenteRepository) {
        this.fileHandler = fileHandler;
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> adresData = fileHandler.readData(path, 1, 2);

        HashMap<RegioCode, int[]> buurten = new HashMap<>();

        for (String[] adres : adresData){
            if (adres[9].contains("Woonfunctie") && adres[71] != null){
                RegioCode buurtCode = new RegioCode(adres[47]);
                updateBuurt(buurten, buurtCode, adres[71]);
            }
        }

        setEnSaveAlleLocaties(buurten);
    }

    private void setEnSaveAlleLocaties(HashMap<RegioCode, int[]> buurten) {
        Set<Gemeente> alleGemeentes = new HashSet<>();

        HashMap<RegioCode, int[]> gemeentes = new HashMap<>();
        HashMap<RegioCode, int[]> wijken = new HashMap<>();

        for (RegioCode buurtCode : buurten.keySet()){
            Gemeente gemeente = alleGemeentes.stream()
                    .filter(g -> g.getRegioCode_id().equals(buurtCode.getGemeenteCode()))
                    .findFirst()
                    .orElseGet(() -> {
                        Gemeente nieuweGemeente = gemeenteRepository.findGemeenteByCode(buurtCode);
                        alleGemeentes.add(nieuweGemeente);
                        return nieuweGemeente;
                    });

            RegioCode wijkCode = buurtCode.getWijkCode();
            Locatie buurt = gemeente.getLocatieByRegioCode(buurtCode);

            int[] ints = buurten.get(buurtCode);

            updateLocatie(gemeentes, gemeente.getRegioCode_id(), ints);
            updateLocatie(wijken, wijkCode, ints);

            double percentage = DataConverterHelper.naarPercentage(ints[0], ints[1]);
            buurt.getLocatieInfo().setPercentageFlatbewoners(percentage);
        }

        for (RegioCode wijkCode : wijken.keySet()){
            Gemeente gemeente = alleGemeentes.stream()
                    .filter(g -> g.getRegioCode_id().equals(wijkCode.getGemeenteCode()))
                    .findFirst().orElseThrow();
            Locatie wijk = gemeente.getLocatieByRegioCode(wijkCode);

            int[] ints = wijken.get(wijkCode);

            double percentage = DataConverterHelper.naarPercentage(ints[0], ints[1]);
            wijk.getLocatieInfo().setPercentageFlatbewoners(percentage);
        }

        for (RegioCode gemeenteCode : gemeentes.keySet()){
            Gemeente gemeente = alleGemeentes.stream()
                    .filter(g -> g.getRegioCode_id().equals(gemeenteCode))
                    .findFirst().orElseThrow();

            int[] ints = gemeentes.get(gemeenteCode);

            double percentage = DataConverterHelper.naarPercentage(ints[0], ints[1]);
            gemeente.getLocatieInfo().setPercentageFlatbewoners(percentage);
        }
        gemeenteRepository.saveAll(alleGemeentes);
    }

    private void updateBuurt(HashMap<RegioCode, int[]> locaties, RegioCode regioCode, String gebouwType){
        int[] waarden = locaties.getOrDefault(regioCode, new int[]{0, 0});
        int woningen = waarden[0] + 1;
        int flats = gebouwType.equals("Flatwoning (overig)") || gebouwType.equals("Appartement") ? waarden[1] + 1 : waarden[1];

        locaties.put(regioCode, new int[]{woningen, flats});
    }

    private void updateLocatie(HashMap<RegioCode, int[]> locaties, RegioCode regioCode, int[] values) {
        int[] waarden = locaties.getOrDefault(regioCode, new int[]{0, 0});
        int woningen = waarden[0] + values[0];
        int flats = waarden[1] + values[1];

        locaties.put(regioCode, new int[]{woningen, flats});
    }
}
