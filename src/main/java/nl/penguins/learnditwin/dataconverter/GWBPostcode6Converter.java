package nl.penguins.learnditwin.dataconverter;

import nl.penguins.learnditwin.dataconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.data.PlaatsRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GWBPostcode6Converter implements DataConverter{
    @Autowired
    private ExcelHandelaar fileHandler;
    @Autowired
    BuurtRepository buurtRepository;
    @Autowired
    PlaatsRepository plaatsRepository;

    @Override
    public void convertData(String path) {
        List<String[]> lineParts = fileHandler.readData(path, 0);

        try{
            Set<Wijk> wijken = new HashSet<>();
            Set<Buurt> buurten = new HashSet<>();

            lineParts.forEach(line -> {
                String wijkCode = line[2];
                String wijkNaam = line[3];
                String buurtCode = line[4];
                String buurtNaam = line[5];
                String postCode6 = line[6];
                String postCode4 = postCode6.substring(0, 4);

                Wijk opgeslagenWijk = wijken.parallelStream().filter(wijk -> wijk.getWijkCode_id().equals(wijkCode)).findFirst().orElse(new Wijk(wijkCode, wijkNaam, postCode4));
                Buurt opgeslagenBuurt = buurten.parallelStream().filter(buurt -> buurt.getBuurtCode_id().equals(buurtCode)).findFirst().orElse(new Buurt(buurtCode, buurtNaam));

                opgeslagenBuurt.addStraat(postCode6);
                opgeslagenWijk.addBuurt(opgeslagenBuurt);

                wijken.add(opgeslagenWijk);
                buurten.add(opgeslagenBuurt);
            });

            plaatsRepository.saveAll(wijken);
            buurtRepository.saveAll(buurten);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}