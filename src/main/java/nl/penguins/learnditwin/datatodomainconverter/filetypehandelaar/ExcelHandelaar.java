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

