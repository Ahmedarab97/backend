package nl.penguins.learnditwin.dataconverter.filetypehandelaar;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@Component
public class ZipUitpakker {
    public InputStream getInputStreamVanZipMogelijkeZip(ClassPathResource resource) {
        try {
            File file = resource.getFile();

            if (file.getName().endsWith(".zip")){
                ZipFile zipFile = new ZipFile(file);

                ZipEntry zipEntry = zipFile.entries().nextElement();
                return zipFile.getInputStream(zipEntry);
            } else return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
