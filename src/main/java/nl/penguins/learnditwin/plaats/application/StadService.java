package nl.penguins.learnditwin.plaats.application;

import nl.penguins.learnditwin.plaats.domain.Plaats;
import nl.penguins.learnditwin.plaats.domain.Stad;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StadService {
    private Stad stad;

    public StadService(){}

    public ArrayList<Plaats> getAllePlaatsen(){
        return stad.getPlaatsen();
    };

    public Plaats getPlaats(String naam){
        for (Plaats plaats : stad.getPlaatsen()) {
            if (plaats.getNaam().equals(naam)) {
                return plaats;
            };
        };
        return null;
    };
};

