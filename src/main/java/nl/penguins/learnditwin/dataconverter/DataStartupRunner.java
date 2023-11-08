package nl.penguins.learnditwin.dataconverter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataStartupRunner implements CommandLineRunner {
    GWBPostcode6Converter GWBPostcode6Converter;

    public DataStartupRunner(GWBPostcode6Converter GWBPostcode6Converter) {
        this.GWBPostcode6Converter = GWBPostcode6Converter;
    }

    @Override
    public void run(String... args) throws Exception {
        GWBPostcode6Converter.convertData("data/Postcodes/GWB2022_pc6.zip");
        System.out.println("data is binnen :D");
    }
}
