package nl.penguins.learnditwin.datatofileconverter;

import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ObjectToFileConverter {
    GemeenteRepository gemeenteRepository;

    public ObjectToFileConverter(GemeenteRepository gemeenteRepository) {
        this.gemeenteRepository = gemeenteRepository;
    }

    public void convertObjectenNaarExcel(String gemeenteNaam, String locatieOpslaan){
        Gemeente gemeente = gemeenteRepository.findByNaam(gemeenteNaam).orElseThrow(() -> new RuntimeException("gemeente niet gevonden"));

        Set<Wijk> wijken = gemeente.getWijken();

        try{
            Workbook workbook = new XSSFWorkbook();
            FileOutputStream fileOutputStream = new FileOutputStream(locatieOpslaan + "gecombineerde-data.xlsx");

            Sheet sheet = workbook.createSheet();

            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("wijkCode");
            headerRow.createCell(1).setCellValue("postCode4");
            headerRow.createCell(2).setCellValue("wijkNaam");

            headerRow.createCell(3).setCellValue("buurtCode");
            headerRow.createCell(4).setCellValue("buurtNaam");
            headerRow.createCell(5).setCellValue("postCode6");
            headerRow.createCell(6).setCellValue("aantalHuishoudens");

            headerRow.createCell(7).setCellValue("percentageHuishoudensMetTaalgroei");
            // Aanvullen voor AlcoholGebruik
            headerRow.createCell(8).setCellValue("percentageVoldoetAanAlcoholRichtlijn");
            headerRow.createCell(9).setCellValue("percentageDrinker");
            headerRow.createCell(10).setCellValue("percentageZwareDrinker");
            headerRow.createCell(11).setCellValue("percentageOvermatigeDrinker");

            // Aanvullen voor Bewegen
            headerRow.createCell(12).setCellValue("percentageVoldoetAanBeweegRichtlijn");
            headerRow.createCell(13).setCellValue("percentageWekelijkseSporter");

            // Aanvullen voor Financieel
            headerRow.createCell(14).setCellValue("percentageMoeiteMetRondkomen");

            // Aanvullen voor Geluidshinder
            headerRow.createCell(15).setCellValue("percentageErnstigeGeluidhinderDoorBuren");

            // Aanvullen voor Gewicht
            headerRow.createCell(16).setCellValue("percentageOndergewicht");
            headerRow.createCell(17).setCellValue("percentageNormaalGewicht");
            headerRow.createCell(18).setCellValue("percentageOvergewicht");
            headerRow.createCell(19).setCellValue("percentageErnstigOvergewicht");

            // Aanvullen voor Gezondheid
            headerRow.createCell(20).setCellValue("percentageGoedOfZeerErvarenGezondheid");

            // Aanvullen voor LichamelijkeBeperkingOfAandoening
            headerRow.createCell(21).setCellValue("percentageEenOfMeerLangdurigeAandoeningen");
            headerRow.createCell(22).setCellValue("percentageBeperktVanwegeGezondheid");
            headerRow.createCell(23).setCellValue("percentageErnstigBeperktVanwegeGezondheid");
            headerRow.createCell(24).setCellValue("percentageLangdurigeZiekteEnBeperkt");
            headerRow.createCell(25).setCellValue("percentageGehoorBeperking");
            headerRow.createCell(26).setCellValue("percentageGezichtBeperking");
            headerRow.createCell(27).setCellValue("percentageMobiliteitBeperking");
            headerRow.createCell(28).setCellValue("percentageEenOfMeerLichamelijkeBeperkingen");

            // Aanvullen voor MentaleProblemen
            headerRow.createCell(29).setCellValue("percentageMatigOfHoogRisicoOpAngstOfDepressie");
            headerRow.createCell(30).setCellValue("percentageHoogRisicoOpAngstOfDepressie");
            headerRow.createCell(31).setCellValue("percentageVeelStressInAfgelopen4Weken");
            headerRow.createCell(32).setCellValue("percentageEenzaam");
            headerRow.createCell(33).setCellValue("percentageErnstigOfZeerEenzaam");
            headerRow.createCell(34).setCellValue("percentageEmotioneelEenzaam");
            headerRow.createCell(35).setCellValue("percentageSociaalEenzaam");

            // Aanvullen voor Ondersteuning
            headerRow.createCell(36).setCellValue("percentageVrijwilligersWerk");
            headerRow.createCell(37).setCellValue("percentageMantelZorger");

            // Aanvullen voor RegieOverEigenLeven
            headerRow.createCell(38).setCellValue("percentageMatigOfVeelRegieOverEigenLeven");

            // Aanvullen voor Roken
            headerRow.createCell(39).setCellValue("percentageRokers");



            AtomicInteger indexExcel = new AtomicInteger(1);
            wijken.forEach(wijk -> {
                        wijk.getBuurten()
                                .forEach(buurt -> {
                                    Row row = sheet.createRow(indexExcel.getAndIncrement());

                                    row.createCell(0).setCellValue(wijk.getWijkCode_id());
                                    row.createCell(1).setCellValue(wijk.getPostcode4());
                                    row.createCell(2).setCellValue(wijk.getNaam());

                                    row.createCell(3).setCellValue(buurt.getBuurtCode_id());
                                    row.createCell(4).setCellValue(buurt.getNaam());
                                    row.createCell(5).setCellValue(buurt.getPostcode6().toString());
                                    row.createCell(6).setCellValue(buurt.getAantalHuishoudens());

                                    for (int i = 6; i <= 39; i++) {
                                        int cellIndex = i;

                                        Optional.ofNullable(buurt.getLaagGeletterdheid())
                                                .map(laagGeletterdheid -> switch (cellIndex){
                                                    case 7 -> laagGeletterdheid.percentageTaalgroei();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));


                                        Optional.ofNullable(buurt.getAlcoholGebruik())
                                                .map(alcoholGebruik -> switch (cellIndex){
                                                    case 8 -> alcoholGebruik.percentageVoldoetAanAlcoholRichtlijn();
                                                    case 9 -> alcoholGebruik.percentageDrinker();
                                                    case 10 -> alcoholGebruik.percentageZwareDrinker();
                                                    case 11 -> alcoholGebruik.percentageOvermatigeDrinker();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getBewegen())
                                                .map(bewegen -> switch (cellIndex){
                                                    case 12 -> bewegen.percentageVoldoetAanBeweegRichtlijn();
                                                    case 13 -> bewegen.percentageWekelijkseSporter();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getFinancieel())
                                                .map(financieel -> switch (cellIndex){
                                                    case 14 -> financieel.percentageMoeiteMetRondkomen();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getGeluidshinder())
                                                .map(financieel -> switch (cellIndex){
                                                    case 15 -> financieel.percentageErnstigeGeluidhinderDoorBuren();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getGewicht())
                                                .map(gewicht -> switch (cellIndex) {
                                                    case 16 -> gewicht.percentageOndergewicht();
                                                    case 17 -> gewicht.percentageNormaalGewicht();
                                                    case 18 -> gewicht.percentageOvergewicht();
                                                    case 19 -> gewicht.percentageErnstigOvergewicht();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));
                                        Optional.ofNullable(buurt.getGezondheid())
                                                .map(gezondheid -> switch (cellIndex) {
                                                    case 20 -> gezondheid.percentageGoedOfZeerErvarenGezondheid();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getLichamelijkeBeperkingOfAandoening())
                                                .map(aandoening -> switch (cellIndex) {
                                                    case 21 -> aandoening.percentageEenOfMeerLangdurigeAandoeningen();
                                                    case 22 -> aandoening.percentageBeperktVanwegeGezondheid();
                                                    case 23 -> aandoening.percentageErnstigBeperktVanwegeGezondheid();
                                                    case 24 -> aandoening.percentageLangdurigeZiekteEnBeperkt();
                                                    case 25 -> aandoening.percentageGehoorBeperking();
                                                    case 26 -> aandoening.percentageGezichtBeperking();
                                                    case 27 -> aandoening.percentageMobiliteitBeperking();
                                                    case 28 -> aandoening.percentageEenOfMeerLichamelijkeBeperkingen();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getMentaleProblemen())
                                                .map(problemen -> switch (cellIndex) {
                                                    case 29 -> problemen.percentageMatigOfHoogRisicoOpAngstOfDepressie();
                                                    case 30 -> problemen.percentageHoogRisicoOpAngstOfDepressie();
                                                    case 31 -> problemen.percentageVeelStressInAfgelopen4Weken();
                                                    case 32 -> problemen.percentageEenzaam();
                                                    case 33 -> problemen.percentageErnstigOfZeerEenzaam();
                                                    case 34 -> problemen.percentageEmotioneelEenzaam();
                                                    case 35 -> problemen.percentageSociaalEenzaam();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getOndersteuning())
                                                .map(ondersteuning -> switch (cellIndex) {
                                                    case 36 -> ondersteuning.percentageVrijwilligersWerk();
                                                    case 37 -> ondersteuning.percentageMantelZorger();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getRegieOverEigenLeven())
                                                .map(regie -> switch (cellIndex) {
                                                    case 38 -> regie.percentageMatigOfVeelRegieOverEigenLeven();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        Optional.ofNullable(buurt.getRoken())
                                                .map(roken -> switch (cellIndex) {
                                                    case 39 -> roken.percentageRokers();
                                                    default -> null;
                                                }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                        }
                                });
                        indexExcel.getAndIncrement();
                    });
            workbook.write(fileOutputStream);
            System.out.println("Excel-bestand succesvol aangemaakt op locatie: " + locatieOpslaan);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
