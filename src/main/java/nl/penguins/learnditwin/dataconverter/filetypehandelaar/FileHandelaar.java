package nl.penguins.learnditwin.dataconverter.filetypehandelaar;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class FileHandelaar {
    @Autowired
    ZipUitpakker zipUitpakker;

    public abstract List<String[]> readData(String path);
}
