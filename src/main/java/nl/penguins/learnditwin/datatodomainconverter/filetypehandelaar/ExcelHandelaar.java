package nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar;

import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelHandelaar extends FileHandelaar {
    private int sheetNummer = 0;
    @Override
    public List<String[]> readData(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream inputStream = super.zipUitpakker.getInputStreamVanZipMogelijkeZip(resource);

            if (inputStream == null) {
                inputStream = resource.getInputStream();
            }
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet sheet = workbook.getSheetAt(sheetNummer);

            List<String[]> ongefilterdeRijen = new ArrayList<>();

            Row row1 = sheet.getRow(0);
            int headerRowLength = row1.getPhysicalNumberOfCells();

            for (Row row : sheet) {
                // TODO: zet integer om naar string
                HashMap<Integer, String> map = new HashMap<>();
                    IntStream.range(0, headerRowLength)
                            .forEach(columnId-> map.put(columnId, null));
                for (Cell cell : row) {
                    map.put(cell.getColumnIndex(), extractCellValue(cell));
                }

                ongefilterdeRijen.add(map.values().toArray(new String[0]));
            }

            return ongefilterdeRijen;
        } catch (IOException e) {
            return null;
        }
    }

    public List<String[]> readData(String path, int optioneelSheetNummer) {
        this.sheetNummer = optioneelSheetNummer;

        return readData(path);
    }

    public List<String[]> readData(String path, int van, int tot, int optioneelSheetNummer) {
        List<String[]> ongefilterdeData = readData(path, optioneelSheetNummer);

        return ongefilterdeData.subList(van, tot);
    }

    public List<String[]> readData(String path, int vanaf, int optioneelSheetNummer) {
        List<String[]> ongefilterdeData = readData(path, optioneelSheetNummer);

        return ongefilterdeData.subList(vanaf, ongefilterdeData.size());
    }
}

