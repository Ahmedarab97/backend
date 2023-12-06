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

            for (Row row : sheet) {
                String[] rowData = new String[row.getLastCellNum()];

                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();

                    if (cell.getCellType() == CellType.NUMERIC) {
                        rowData[columnIndex] = String.valueOf(cell.getNumericCellValue());
                    } else if (cell.getCellType() == CellType.STRING) {
                        rowData[columnIndex] = cell.getStringCellValue();
                    } else {
                        rowData[columnIndex] = null;
                    }
                }

                ongefilterdeRijen.add(rowData);
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

