package nl.penguins.learnditwin.datatodomainconverter.filetypehandelaar;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class FileHandelaar {
    @Autowired
    ZipUitpakker zipUitpakker;

    // TODO: pas parameter namen aan
    public abstract List<String[]> readData(String path, int optioneelSheetNummer);
    public abstract List<String[]> readData(String path, int vanaf, int tot, int optioneelSheetNummer);
    public abstract List<String[]> readData(String path, int vanaf, int optioneelSheetNummer);
}
