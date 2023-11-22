package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.data.WijkRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Component
public class AlleCijfersOverzichtGemeenteConverter implements DataConverter {
    private final ExcelHandelaar excelHandelaar;
    private final GemeenteRepository gemeenteRepository;
    private final WijkRepository wijkRepository;
    private final BuurtRepository buurtRepository;

    public AlleCijfersOverzichtGemeenteConverter(ExcelHandelaar excelHandelaar, GemeenteRepository gemeenteRepository, WijkRepository wijkRepository, BuurtRepository buurtRepository) {
        this.excelHandelaar = excelHandelaar;
        this.gemeenteRepository = gemeenteRepository;
        this.wijkRepository = wijkRepository;
        this.buurtRepository = buurtRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> inwonersAantallen = excelHandelaar.readData(path, 1, 63, 0);
        List<String[]> jongerDan15 = excelHandelaar.readData(path, 1, 63, 1);
        List<String[]> ouderDan65 = excelHandelaar.readData(path, 1, 63, 2);
        List<String[]> huisHoudenData = excelHandelaar.readData(path, 1, 63, 3);

        for (String[] inwonerRegel : inwonersAantallen) {
            String soortRegio = inwonerRegel[1];
            String regioCode = inwonerRegel[2];
            int inwonerAantal = (int) Double.parseDouble(inwonerRegel[14]);

            switch (soortRegio) {
                case "Gemeente" -> {
                    Gemeente locatie = gemeenteRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    locatie.setAantalHuishoudens(inwonerAantal);

                    gemeenteRepository.save(locatie);
                }
                case "Wijk" -> {
                    Wijk locatie = wijkRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    locatie.setAantalHuishoudens(inwonerAantal);

                    wijkRepository.save(locatie);
                }
                case "Buurt" -> {
                    Buurt locatie = buurtRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    locatie.setAantalHuishoudens(inwonerAantal);

                    buurtRepository.save(locatie);
                }
                default -> {
                    continue;
                }
            }
        }

        for (String[] jongerDan15Regel : jongerDan15){
            String soortRegio = jongerDan15Regel[1];
            String regioCode = jongerDan15Regel[2];
            double percentageJongerDan15In2023 = Double.parseDouble(jongerDan15Regel[14]);

            switch (soortRegio) {
                case "Gemeente" -> {
                    Gemeente locatie = gemeenteRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double afgerondPercentageJongerDan15 = afronden(percentageJongerDan15In2023);
                    locatie.getLocatieInfo().getLeeftijd().setPercentageJongerDan15(afgerondPercentageJongerDan15);

                    gemeenteRepository.save(locatie);
                }
                case "Wijk" -> {
                    Wijk locatie = wijkRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double afgerondPercentageJongerDan15 = afronden(percentageJongerDan15In2023);
                    locatie.getLocatieInfo().getLeeftijd().setPercentageJongerDan15(afgerondPercentageJongerDan15);

                    wijkRepository.save(locatie);
                }
                case "Buurt" -> {
                    Buurt locatie = buurtRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double afgerondPercentageJongerDan15 = afronden(percentageJongerDan15In2023);
                    locatie.getLocatieInfo().getLeeftijd().setPercentageJongerDan15(afgerondPercentageJongerDan15);

                    buurtRepository.save(locatie);
                }
                default -> {
                    continue;
                }
            }
        }

        for (String[] ouderDan65Regel : ouderDan65){
            String soortLocatie = ouderDan65Regel[1];
            String regioCode = ouderDan65Regel[2];
            double percentageOuderDan65In2023 = Double.parseDouble(ouderDan65Regel[14]);
            switch (soortLocatie){
                case "Gemeente" -> {
                    Gemeente locatie = gemeenteRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double afgerondPercentageOuderDan65 = afronden(percentageOuderDan65In2023);
                    locatie.getLocatieInfo().getLeeftijd().setPercentageOuderDan65(afgerondPercentageOuderDan65);

                    gemeenteRepository.save(locatie);
                }
                case "Wijk" -> {
                    Wijk locatie = wijkRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double afgerondPercentageOuderDan65 = afronden(percentageOuderDan65In2023);
                    locatie.getLocatieInfo().getLeeftijd().setPercentageOuderDan65(afgerondPercentageOuderDan65);

                    wijkRepository.save(locatie);
                }
                case "Buurt" -> {
                    Buurt locatie = buurtRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double afgerondPercentageOuderDan65 = afronden(percentageOuderDan65In2023);
                    locatie.getLocatieInfo().getLeeftijd().setPercentageOuderDan65(afgerondPercentageOuderDan65);

                    buurtRepository.save(locatie);
                }
                default -> {
                    continue;
                }
            }
        }

        for (String[] huisHouden : huisHoudenData){
            String soortLocatie = huisHouden[1];
            String regioCode = huisHouden[2];

            int aantalHuishoudens1Persoons = (int) Double.parseDouble(huisHouden[4]);


            switch (soortLocatie){
                case "Gemeente" -> {
                    Gemeente locatie = gemeenteRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double percentage = naarPercentage(locatie.getAantalInwoners(), aantalHuishoudens1Persoons);
                    locatie.getLocatieInfo().setHuishouden(percentage);

                    gemeenteRepository.save(locatie);
                }
                case "Wijk" -> {
                    Wijk locatie = wijkRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double percentage = naarPercentage(locatie.getAantalInwoners(), aantalHuishoudens1Persoons);
                    locatie.getLocatieInfo().setHuishouden(percentage);

                    wijkRepository.save(locatie);
                }
                case "Buurt" -> {
                    Buurt locatie = buurtRepository.findById(regioCode).orElseThrow(() -> new RuntimeException("GemeenteNotFound"));

                    double percentage = naarPercentage(locatie.getAantalInwoners(), aantalHuishoudens1Persoons);
                    locatie.getLocatieInfo().setHuishouden(percentage);

                    buurtRepository.save(locatie);
                }
                default -> {
                    continue;
                }
            }
        }
    }

    private double naarPercentage(double totaalInwoners, double deel){
        double percentageAfgerond = (double) deel / totaalInwoners;
        return afronden(percentageAfgerond);
    }

    private double afronden(double getal){
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return Double.parseDouble(df.format(getal));
    }
}
