package nl.penguins.learnditwin.layers.application;

import nl.penguins.learnditwin.layers.domain.Verband;
import org.springframework.stereotype.Service;

@Service
public class VerbandService {
    public String getVerband(String attribuutTeVergelijken, String optioneelMetTeVergelijken, Long objectTeVergelijken, Long objectenGemiddelde) {
//        Verband<T> verband = Verband.genereerVerband(attribuutTeVergelijken, objectTeVergelijken, objectenGemiddelde);

//        return verband.getVerbandUitleg();
        return "verband";
    }

    public String getVerband() {
        TijdelijkStad stad1 = new TijdelijkStad("Amsterdam", 850000, 80);
        TijdelijkStad gemiddeldeStad = new TijdelijkStad("Gemiddelde", 750000, 93);

        Verband<TijdelijkStad> geletterdheid = Verband.genereerVerband("Geletterdheid", "BevolkingsAantal", stad1, gemiddeldeStad);

        return geletterdheid.getVerbandUitleg();
    }
}

