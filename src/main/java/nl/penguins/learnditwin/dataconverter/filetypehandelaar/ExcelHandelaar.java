package nl.penguins.learnditwin.dataconverter.filetypehandelaar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
    public List<String[]> readData(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            InputStream optioneelInputStream = super.zipUitpakker.getInputStreamVanZipMogelijkeZip(resource);

            if (optioneelInputStream == null){
                optioneelInputStream = resource.getInputStream();
            }
            Workbook workbook = WorkbookFactory.create(optioneelInputStream);

            Sheet sheet = workbook.getSheetAt(0);

            return StreamSupport.stream(sheet.spliterator(), false)
                    .skip(1)
                    .map(row -> StreamSupport.stream(row.spliterator(), false)
                            .map(Cell::getStringCellValue)
                            .toArray(String[]::new))
                    .collect(Collectors.toList());
            } catch (IOException e){
                return null;
        }
    }
}

