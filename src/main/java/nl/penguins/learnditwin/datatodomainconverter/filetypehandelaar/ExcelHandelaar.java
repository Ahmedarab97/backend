package nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar;

import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ExcelHandelaar extends FileHandelaar{
    @Override
    public List<String[]> readData(String path, int optioneelSheetNummer) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream optioneelInputStream = super.zipUitpakker.getInputStreamVanZipMogelijkeZip(resource);

            if (optioneelInputStream == null){
                optioneelInputStream = resource.getInputStream();
            }
            Workbook workbook = WorkbookFactory.create(optioneelInputStream);

            Sheet sheet = workbook.getSheetAt(optioneelSheetNummer);

            return StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1)
                    .map(row -> StreamSupport.stream(row.spliterator(), false)
                            .map(cell -> {
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    return String.valueOf(cell.getNumericCellValue());
                                } else if (cell.getCellType() == CellType.STRING) {
                                    return cell.getStringCellValue();
                                } else {
                                    return ""; // Voeg hier logica toe voor andere celtypen indien nodig
                                }
                            })
                            .toArray(String[]::new))
                    .collect(Collectors.toList());
            } catch (IOException e){
                return null;
        }
    }
}

