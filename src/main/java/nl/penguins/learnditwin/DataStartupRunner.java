package nl.penguins.learnditwin;

import nl.penguins.learnditwin.datatodomainconverter.AlleCijfersGezondheidMonitorConverter;
import nl.penguins.learnditwin.datatodomainconverter.AlleCijfersOverzichtGemeenteConverter;
import nl.penguins.learnditwin.datatodomainconverter.GWBLaaggeletterdheidConverter;
import nl.penguins.learnditwin.datatodomainconverter.GWBPostcode6Converter;
import nl.penguins.learnditwin.datatofileconverter.ObjectToFileConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DataStartupRunner implements CommandLineRunner {
    nl.penguins.learnditwin.datatodomainconverter.GWBPostcode6Converter GWBPostcode6Converter;
    GWBLaaggeletterdheidConverter laaggeletterdheidConverter;
    AlleCijfersGezondheidMonitorConverter alleCijfersGezondheidMonitorConverter;
    AlleCijfersOverzichtGemeenteConverter alleCijfersOverzichtGemeenteConverter;
    ObjectToFileConverter objectToFileConverter;

    public DataStartupRunner(GWBPostcode6Converter GWBPostcode6Converter, GWBLaaggeletterdheidConverter laaggeletterdheidConverter, AlleCijfersGezondheidMonitorConverter alleCijfersGezondheidMonitorConverter, ObjectToFileConverter objectToFileConverter, AlleCijfersOverzichtGemeenteConverter alleCijfersOverzichtGemeenteConverter) {
        this.GWBPostcode6Converter = GWBPostcode6Converter;
        this.laaggeletterdheidConverter = laaggeletterdheidConverter;
        this.alleCijfersGezondheidMonitorConverter = alleCijfersGezondheidMonitorConverter;
        this.objectToFileConverter = objectToFileConverter;
        this.alleCijfersOverzichtGemeenteConverter = alleCijfersOverzichtGemeenteConverter;
    }

    @Override
    public void run(String... args) throws Exception {
        GWBPostcode6Converter.convertData("data/Postcodes/GWB2022_pc6.zip");

        System.out.println("Begin laaggeletterdheid data laden");
        getPathFromFilesInMap("data/Laaggeletterdheid")
                .forEach(path -> laaggeletterdheidConverter.convertData(path));
        System.out.println("Laaggeletterdheid data binnen");

        alleCijfersGezondheidMonitorConverter.convertData("data/allecijfers/gezondheidsmonitor-gemeente-nieuwegein.xlsx");
        alleCijfersOverzichtGemeenteConverter.convertData("data/allecijfers/overzicht-nieuwegein.xlsx");
//        objectToFileConverter.convertObjectenNaarExcel("Nieuwegein", "data");
    }

    private List<String> getPathFromFilesInMap(String mapPath) throws IOException {
        List<String> paden = new ArrayList<>();

        ClassPathResource resource = new ClassPathResource(mapPath);
        InputStream inputStream = resource.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            paden.add(String.format("%s/%s", mapPath, line));
        }
        return paden;
    }
}
