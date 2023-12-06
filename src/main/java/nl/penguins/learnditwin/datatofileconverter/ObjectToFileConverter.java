package nl.penguins.learnditwin.datatofileconverter;

import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
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

    public void convertObjectenNaarExcel(String gemeenteNaam, String locatieOpslaan) throws FileNotFoundException {
        Gemeente gemeente = gemeenteRepository.findByNaam(gemeenteNaam).orElseThrow();

        Set<Wijk> wijken = gemeente.getWijken();

        try {
            Workbook workbook = new XSSFWorkbook();
            FileOutputStream fileOutputStream = new FileOutputStream(locatieOpslaan + "gecombineerde-data.xlsx");

            Sheet sheet = workbook.createSheet();

            Row headerRow = sheet.createRow(0);

            headerRow.createCell(0).setCellValue("wijkCode");
            headerRow.createCell(1).setCellValue("postCode4");
            headerRow.createCell(2).setCellValue("wijkNaam");
            headerRow.createCell(3).setCellValue("aantalInwoners");

            headerRow.createCell(4).setCellValue("percentageHuishoudensMetTaalgroei");
            // Aanvullen voor AlcoholGebruik
            headerRow.createCell(5).setCellValue("percentageVoldoetAanAlcoholRichtlijn");
            headerRow.createCell(6).setCellValue("percentageDrinker");
            headerRow.createCell(7).setCellValue("percentageZwareDrinker");
            headerRow.createCell(8).setCellValue("percentageOvermatigeDrinker");

            // Aanvullen voor Bewegen
            headerRow.createCell(9).setCellValue("percentageVoldoetAanBeweegRichtlijn");
            headerRow.createCell(10).setCellValue("percentageWekelijkseSporter");

            // Aanvullen voor Financieel
            headerRow.createCell(11).setCellValue("percentageMoeiteMetRondkomen");
            headerRow.createCell(12).setCellValue("percentageBijstand");

            // Aanvullen voor Geluidshinder
            headerRow.createCell(13).setCellValue("percentageErnstigeGeluidhinderDoorBuren");

            // Aanvullen voor Gewicht
            headerRow.createCell(14).setCellValue("percentageOndergewicht");
            headerRow.createCell(15).setCellValue("percentageNormaalGewicht");
            headerRow.createCell(16).setCellValue("percentageOvergewicht");
            headerRow.createCell(17).setCellValue("percentageErnstigOvergewicht");

            // Aanvullen voor Gezondheid
            headerRow.createCell(18).setCellValue("percentageGoedOfZeerErvarenGezondheid");

            // Aanvullen voor LichamelijkeBeperkingOfAandoening
            headerRow.createCell(19).setCellValue("percentageEenOfMeerLangdurigeAandoeningen");
            headerRow.createCell(20).setCellValue("percentageBeperktVanwegeGezondheid");
            headerRow.createCell(21).setCellValue("percentageErnstigBeperktVanwegeGezondheid");
            headerRow.createCell(22).setCellValue("percentageLangdurigeZiekteEnBeperkt");
            headerRow.createCell(23).setCellValue("percentageGehoorBeperking");
            headerRow.createCell(24).setCellValue("percentageGezichtBeperking");
            headerRow.createCell(25).setCellValue("percentageMobiliteitBeperking");
            headerRow.createCell(26).setCellValue("percentageEenOfMeerLichamelijkeBeperkingen");

            // Aanvullen voor MentaleProblemen
            headerRow.createCell(27).setCellValue("percentageMatigOfHoogRisicoOpAngstOfDepressie");
            headerRow.createCell(28).setCellValue("percentageHoogRisicoOpAngstOfDepressie");
            headerRow.createCell(29).setCellValue("percentageVeelStressInAfgelopen4Weken");
            headerRow.createCell(30).setCellValue("percentageEenzaam");
            headerRow.createCell(31).setCellValue("percentageErnstigOfZeerEenzaam");
            headerRow.createCell(32).setCellValue("percentageEmotioneelEenzaam");
            headerRow.createCell(33).setCellValue("percentageSociaalEenzaam");

            // Aanvullen voor Ondersteuning
            headerRow.createCell(34).setCellValue("percentageVrijwilligersWerk");
            headerRow.createCell(35).setCellValue("percentageMantelZorger");

            // Aanvullen voor RegieOverEigenLeven
            headerRow.createCell(36).setCellValue("percentageMatigOfVeelRegieOverEigenLeven");

            // Aanvullen voor Roken
            headerRow.createCell(37).setCellValue("percentageRokers");

            headerRow.createCell(38).setCellValue("percentageJongerDan15");
            headerRow.createCell(39).setCellValue("percentageOuderDan65");

            headerRow.createCell(40).setCellValue("percentageEenpersoonsHuishouden");

            headerRow.createCell(41).setCellValue("aantalInwonersPerKilometer2");
            headerRow.createCell(42).setCellValue("woningPrijs");
            headerRow.createCell(43).setCellValue("percentageFlats");

            AtomicInteger indexExcel = new AtomicInteger(1);
            wijken.forEach(wijk -> {
                            Row row = sheet.createRow(indexExcel.getAndIncrement());

                            row.createCell(0).setCellValue(wijk.getRegioCode_id().regioCode());
                            row.createCell(1).setCellValue(wijk.getPostcode4());
                            row.createCell(2).setCellValue(wijk.getNaam());
                            row.createCell(3).setCellValue(wijk.getAantalInwoners());

                            for (int i = 4; i <= 43; i++) {
                                int cellIndex = i;

                                Optional.ofNullable(wijk.getLaagGeletterdheid())
                                        .map(laagGeletterdheid -> switch (cellIndex) {
                                            case 4 -> laagGeletterdheid.percentageTaalgroei();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));


                                Optional.ofNullable(wijk.getLocatieInfo().getAlcoholGebruik())
                                        .map(alcoholGebruik -> switch (cellIndex) {
                                            case 5 -> alcoholGebruik.percentageVoldoetAanAlcoholRichtlijn();
                                            case 6 -> alcoholGebruik.percentageDrinker();
                                            case 7 -> alcoholGebruik.percentageZwareDrinker();
                                            case 8 -> alcoholGebruik.percentageOvermatigeDrinker();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getBewegen())
                                        .map(bewegen -> switch (cellIndex) {
                                            case 9 -> bewegen.percentageVoldoetAanBeweegRichtlijn();
                                            case 10 -> bewegen.percentageWekelijkseSporter();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getFinancieel())
                                        .map(financieel -> switch (cellIndex) {
                                            case 11 -> financieel.getPercentageMoeiteMetRondkomen();
                                            case 12 -> financieel.getPercentageBijstand();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getGeluidshinder())
                                        .map(financieel -> switch (cellIndex) {
                                            case 13 -> financieel.percentageErnstigeGeluidhinderDoorBuren();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getGewicht())
                                        .map(gewicht -> switch (cellIndex) {
                                            case 14 -> gewicht.percentageOndergewicht();
                                            case 15 -> gewicht.percentageNormaalGewicht();
                                            case 16 -> gewicht.percentageOvergewicht();
                                            case 17 -> gewicht.percentageErnstigOvergewicht();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));
                                Optional.ofNullable(wijk.getLocatieInfo().getGezondheid())
                                        .map(gezondheid -> switch (cellIndex) {
                                            case 18 -> gezondheid.percentageGoedOfZeerErvarenGezondheid();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getLichamelijkeBeperkingOfAandoening())
                                        .map(aandoening -> switch (cellIndex) {
                                            case 19 -> aandoening.percentageEenOfMeerLangdurigeAandoeningen();
                                            case 20 -> aandoening.percentageBeperktVanwegeGezondheid();
                                            case 21 -> aandoening.percentageErnstigBeperktVanwegeGezondheid();
                                            case 22 -> aandoening.percentageLangdurigeZiekteEnBeperkt();
                                            case 23 -> aandoening.percentageGehoorBeperking();
                                            case 24 -> aandoening.percentageGezichtBeperking();
                                            case 25 -> aandoening.percentageMobiliteitBeperking();
                                            case 26 -> aandoening.percentageEenOfMeerLichamelijkeBeperkingen();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getMentaleProblemen())
                                        .map(problemen -> switch (cellIndex) {
                                            case 27 -> problemen.percentageMatigOfHoogRisicoOpAngstOfDepressie();
                                            case 28 -> problemen.percentageHoogRisicoOpAngstOfDepressie();
                                            case 29 -> problemen.percentageVeelStressInAfgelopen4Weken();
                                            case 30 -> problemen.percentageEenzaam();
                                            case 31 -> problemen.percentageErnstigOfZeerEenzaam();
                                            case 32 -> problemen.percentageEmotioneelEenzaam();
                                            case 33 -> problemen.percentageSociaalEenzaam();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getOndersteuning())
                                        .map(ondersteuning -> switch (cellIndex) {
                                            case 34 -> ondersteuning.percentageVrijwilligersWerk();
                                            case 35 -> ondersteuning.percentageMantelZorger();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getRegieOverEigenLeven())
                                        .map(regie -> switch (cellIndex) {
                                            case 36 -> regie.percentageMatigOfVeelRegieOverEigenLeven();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getRoken())
                                        .map(roken -> switch (cellIndex) {
                                            case 37 -> roken.percentageRokers();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getLeeftijd())
                                        .map(leeftijd -> switch (cellIndex) {
                                            case 38 -> leeftijd.getPercentageJongerDan15();
                                            case 39 -> leeftijd.getPercentageOuderDan65();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getHuishouden())
                                        .map(huishouden -> switch (cellIndex) {
                                            case 40 -> huishouden.percentage1PersoonsHuishoudens();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));


                                Optional.ofNullable(wijk.getLocatieInfo().getWoonOmstandigheden())
                                        .map(woonOmstandigheden -> switch (cellIndex) {
                                            case 41 -> woonOmstandigheden.getAantalInwonersPerKilometer2();
                                            case 42 -> woonOmstandigheden.getWoningPrijs();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));

                                Optional.ofNullable(wijk.getLocatieInfo().getWoonOmstandigheden())
                                        .map(woonOmstandigheden -> switch (cellIndex) {
                                            case 43 -> woonOmstandigheden.getPercentageFlatBewoners();
                                            default -> null;
                                        }).ifPresent(value -> row.createCell(cellIndex).setCellValue(value));
                            }
                        });
                indexExcel.getAndIncrement();
            workbook.write(fileOutputStream);
            System.out.println("Excel-bestand succesvol aangemaakt op locatie: " + locatieOpslaan);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
