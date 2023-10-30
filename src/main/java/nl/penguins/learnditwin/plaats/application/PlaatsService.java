package nl.penguins.learnditwin.plaats.application;

import nl.penguins.learnditwin.plaats.domain.Plaats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class PlaatsService {
    private ArrayList<String> postcodes = new ArrayList<>();

    public PlaatsService(){};

    public ArrayList<String> getAllePostcode() {
        return postcodes;
    }; // Ik maak zo nog een klasse voor postcodes waar de indicatoren aan gebonden kunnen worden?
};
