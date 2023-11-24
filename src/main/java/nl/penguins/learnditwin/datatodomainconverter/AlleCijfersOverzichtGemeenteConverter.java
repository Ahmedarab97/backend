package nl.penguins.learnditwin.datatodomainconverter;

import nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar.ExcelHandelaar;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Locatie;
import nl.penguins.learnditwin.plaats.domain.ids.RegioCode;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Component
public class AlleCijfersOverzichtGemeenteConverter implements DataConverter {
    private final ExcelHandelaar excelHandelaar;
    private final GemeenteRepository gemeenteRepository;

    public AlleCijfersOverzichtGemeenteConverter(ExcelHandelaar excelHandelaar, GemeenteRepository gemeenteRepository) {
        this.excelHandelaar = excelHandelaar;
        this.gemeenteRepository = gemeenteRepository;
    }

    @Override
    public void convertData(String path) {
        List<String[]> inwonersAantallen = excelHandelaar.readData(path, 1, 63, 0);
        List<String[]> jongerDan15 = excelHandelaar.readData(path, 1, 63, 1);
        List<String[]> ouderDan65 = excelHandelaar.readData(path, 1, 63, 2);
        List<String[]> huisHoudenData = excelHandelaar.readData(path, 1, 63, 3);

        for (String[] inwonerRegel : inwonersAantallen) {
            String soortRegio = inwonerRegel[1];
            String stringRegioCode = inwonerRegel[2];
            int inwonerAantal = (int) Double.parseDouble(inwonerRegel[14]);
            RegioCode regioCode = new RegioCode(stringRegioCode);

            try {
                Gemeente gemeente = gemeenteRepository.findGemeenteByCode(regioCode);
                Locatie locatie = gemeente.getLocatieByRegioCode(regioCode);

                locatie.setAantalHuishoudens(inwonerAantal);
                gemeenteRepository.save(gemeente);
            } catch (Exception e){
                // todo specifieke error voor findGemeenteByCode
                continue;
            }
        }

        for (String[] jongerDan15Regel : jongerDan15){
            String soortRegio = jongerDan15Regel[1];
            String stringRegioCode = jongerDan15Regel[2];
            double percentageJongerDan15In2023 = Double.parseDouble(jongerDan15Regel[14]);
            RegioCode regioCode = new RegioCode(stringRegioCode);

            try {
                Gemeente gemeente = gemeenteRepository.findGemeenteByCode(regioCode);
                Locatie locatie = gemeente.getLocatieByRegioCode(regioCode);

                double afgerondPercentageJongerDan15 = afronden(percentageJongerDan15In2023);
                locatie.getLocatieInfo().getLeeftijd().setPercentageJongerDan15(afgerondPercentageJongerDan15);
                gemeenteRepository.save(gemeente);
            } catch (Exception e){
                // todo specifieke error voor findGemeenteByCode
                continue;
            }
        }

        for (String[] ouderDan65Regel : ouderDan65){
            String soortLocatie = ouderDan65Regel[1];
            String stringRegioCode = ouderDan65Regel[2];
            double percentageOuderDan65In2023 = Double.parseDouble(ouderDan65Regel[14]);
            RegioCode regioCode = new RegioCode(stringRegioCode);

            try {
                Gemeente gemeente = gemeenteRepository.findGemeenteByCode(regioCode);
                Locatie locatie = gemeente.getLocatieByRegioCode(regioCode);

                double afgerondPercentageOuderDan65 = afronden(percentageOuderDan65In2023);
                locatie.getLocatieInfo().getLeeftijd().setPercentageOuderDan65(afgerondPercentageOuderDan65);

                gemeenteRepository.save(gemeente);
            } catch (Exception e){
                // todo specifieke error voor findGemeenteByCode
                continue;
            }
        }

        for (String[] huisHouden : huisHoudenData){
            String soortLocatie = huisHouden[1];
            String stringRegioCode = huisHouden[2];

            int aantalHuishoudens1Persoons = (int) Double.parseDouble(huisHouden[4]);
            RegioCode regioCode = new RegioCode(stringRegioCode);

            try {
                Gemeente gemeente = gemeenteRepository.findGemeenteByCode(regioCode);
                Locatie locatie = gemeente.getLocatieByRegioCode(regioCode);

                double percentage = naarPercentage(locatie.getAantalInwoners(), aantalHuishoudens1Persoons);
                locatie.getLocatieInfo().setHuishouden(percentage);

                gemeenteRepository.save(gemeente);
            } catch (Exception e){
                // todo specifieke error voor findGemeenteByCode
                continue;
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
