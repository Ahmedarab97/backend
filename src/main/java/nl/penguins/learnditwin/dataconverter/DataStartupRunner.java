package nl.penguins.learnditwin.dataconverter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DataStartupRunner implements CommandLineRunner {
    GWBPostcode6Converter GWBPostcode6Converter;
    GWBLaaggeletterdheidConverter laaggeletterdheidConverter;

    public DataStartupRunner(GWBPostcode6Converter GWBPostcode6Converter, GWBLaaggeletterdheidConverter laaggeletterdheidConverter) {
        this.GWBPostcode6Converter = GWBPostcode6Converter;
        this.laaggeletterdheidConverter = laaggeletterdheidConverter;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Begin postcode data laden");
        GWBPostcode6Converter.convertData("data/Postcodes/GWB2022_pc6.zip");
        System.out.println("Postcode data binnen");

        System.out.println("Begin laaggeletterdheid data laden");
        getPathFromFilesInMap("data/Laaggeletterdheid")
                .stream()
                .forEach(path -> laaggeletterdheidConverter.convertData(path));
        System.out.println("Laaggeletterdheid data binnen");
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
