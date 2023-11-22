package nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar;

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

            if (file.getName().endsWith(".zip")) {
                ZipFile zipFile = new ZipFile(file);

                ZipEntry zipEntry = zipFile.entries().nextElement();
                return zipFile.getInputStream(zipEntry);
            } else return null;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
