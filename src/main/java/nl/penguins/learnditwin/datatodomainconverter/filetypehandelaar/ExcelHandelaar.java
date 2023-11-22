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
public class ExcelHandelaar extends FileHandelaar {
    @Override
    public List<String[]> readData(String path, int optioneelSheetNummer) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream optioneelInputStream = super.zipUitpakker.getInputStreamVanZipMogelijkeZip(resource);

            if (optioneelInputStream == null) {
                optioneelInputStream = resource.getInputStream();
            }
            Workbook workbook = WorkbookFactory.create(optioneelInputStream);

            Sheet sheet = workbook.getSheetAt(optioneelSheetNummer);

            List<String[]> ongefilterdeRijen = StreamSupport.stream(sheet.spliterator(), false)
                    .map(row -> StreamSupport.stream(row.spliterator(), false)
                            .map(cell -> {
                                if (cell.getCellType() == CellType.NUMERIC) {
                                    return String.valueOf(cell.getNumericCellValue());
                                } else if (cell.getCellType() == CellType.STRING) {
                                    return cell.getStringCellValue();
                                } else {
                                    return "";
                                }
                            })
                            .toArray(String[]::new)).toList();
            return ongefilterdeRijen;
            // TODO sublist()
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public List<String[]> readData(String path, int van, int tot, int optioneelSheetNummer) {
        List<String[]> ongefilterdeData = readData(path, optioneelSheetNummer);

        return ongefilterdeData.subList(van, tot);
    }

    @Override
    public List<String[]> readData(String path, int vanaf, int optioneelSheetNummer) {
        List<String[]> ongefilterdeData = readData(path, optioneelSheetNummer);

        return ongefilterdeData.subList(vanaf, ongefilterdeData.size());
    }
}

