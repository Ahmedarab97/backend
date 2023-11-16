package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.data.WijkRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GWBPostcode6Converter implements DataConverter {
    private final ExcelHandelaar fileHandler;
    private final BuurtRepository buurtRepository;
    private final WijkRepository wijkRepository;
    private final GemeenteRepository gemeenteRepository;

    public GWBPostcode6Converter(ExcelHandelaar fileHandler, BuurtRepository buurtRepository, WijkRepository wijkRepository, GemeenteRepository gemeenteRepository) {
        this.fileHandler = fileHandler;
        this.buurtRepository = buurtRepository;
        this.wijkRepository = wijkRepository;
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    public void convertData(String path) {
        System.out.println("Begin postcode data laden");

        List<String[]> lineParts = fileHandler.readData(path, 0);

        try {
            Set<Gemeente> gemeentes = new HashSet<>();
            Set<Wijk> wijken = new HashSet<>();
            Set<Buurt> buurten = new HashSet<>();

            lineParts.forEach(line -> {
                String gemeenteCode = line[0];
                String gemeenteNaam = line[1];

                if (gemeenteNaam.equals("Nieuwegein")) {
                    String wijkCode = line[2];
                    String wijkNaam = line[3];
                    String buurtCode = line[4];
                    String buurtNaam = line[5];
                    String postCode6 = line[6];
                    String postCode4 = postCode6.substring(0, 4);

                    Wijk opgeslagenWijk = wijken.parallelStream().filter(wijk -> wijk.getWijkCode_id().equals(wijkCode)).findFirst().orElse(new Wijk(wijkCode, wijkNaam, postCode4));
                    Buurt opgeslagenBuurt = buurten.parallelStream().filter(buurt -> buurt.getBuurtCode_id().equals(buurtCode)).findFirst().orElse(new Buurt(buurtCode, buurtNaam));
                    Gemeente opgeslagenGemeente = gemeentes.parallelStream().filter(gemeente -> gemeente.getGemeenteCode_id().equals(gemeenteCode)).findFirst().orElse(new Gemeente(gemeenteCode, gemeenteNaam));

                    opgeslagenBuurt.addStraat(postCode6);
                    opgeslagenWijk.addBuurt(opgeslagenBuurt);
                    opgeslagenGemeente.voegWijkToe(opgeslagenWijk);

                    wijken.add(opgeslagenWijk);
                    buurten.add(opgeslagenBuurt);
                    gemeentes.add(opgeslagenGemeente);
                }
            });

            wijkRepository.saveAll(wijken);
            buurtRepository.saveAll(buurten);
            gemeenteRepository.saveAll(gemeentes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Postcode data binnen");
        }
    }
}