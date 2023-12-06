package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.ids.RegioCode;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GWBPostcode6Converter implements DataConverter {
    private final ExcelHandelaar fileHandler;
    private final GemeenteRepository gemeenteRepository;

    public GWBPostcode6Converter(ExcelHandelaar fileHandler, GemeenteRepository gemeenteRepository) {
        this.fileHandler = fileHandler;
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    public void convertData(String path) {
        System.out.println("Begin postcode data laden");

        List<String[]> lineParts = fileHandler.readData(path, 1, 0);

        try {
            Set<Gemeente> gemeentes = new HashSet<>();
            Set<Wijk> wijken = new HashSet<>();
            Set<Buurt> buurten = new HashSet<>();

            lineParts.forEach(line -> {
                RegioCode gemeenteCode = new RegioCode(line[0]);
                String gemeenteNaam = line[1];

                if (gemeenteNaam.equals("Nieuwegein")) {
                    RegioCode wijkCode = new RegioCode(line[2]);
                    String wijkNaam = line[3];
                    RegioCode buurtCode = new RegioCode(line[4]);
                    String buurtNaam = line[5];
                    String postCode6 = line[6];
                    String postCode4 = postCode6.substring(0, 4);

                    // TODO: misschien een of andere factory?
                    //  gooi er een regio code in, kijkt wat voor locatie het is.
                    Wijk opgeslagenWijk = wijken.parallelStream().filter(wijk -> wijk.getRegioCode_id().equals(wijkCode)).findFirst().orElse(new Wijk(wijkCode, wijkNaam, postCode4));
                    Buurt opgeslagenBuurt = buurten.parallelStream().filter(buurt -> buurt.getRegioCode_id().equals(buurtCode)).findFirst().orElse(new Buurt(buurtCode, buurtNaam));
                    Gemeente opgeslagenGemeente = gemeentes.parallelStream().filter(gemeente -> gemeente.getRegioCode_id().equals(gemeenteCode)).findFirst().orElse(new Gemeente(gemeenteCode, gemeenteNaam));

                    opgeslagenBuurt.toevoegenPostcode6(postCode6);
                    opgeslagenWijk.addBuurt(opgeslagenBuurt);
                    opgeslagenGemeente.voegWijkToe(opgeslagenWijk);

                    wijken.add(opgeslagenWijk);
                    buurten.add(opgeslagenBuurt);
                    gemeentes.add(opgeslagenGemeente);
                }
            });

            gemeenteRepository.saveAll(gemeentes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Postcode data binnen");
        }
    }
}