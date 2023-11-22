package nl.penguins.learnditwin.plaats.application;

//import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
//import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.application.dto.GemeenteDTO;
import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GemeenteService {
    private final GemeenteRepository gemeenteRepository;

    public GemeenteService(GemeenteRepository gemeenteRepository) {
        this.gemeenteRepository = gemeenteRepository;
    }

    public GemeenteDTO getAlleBuurtenVanGemeente(String gemeenteNaam) {
        String gemeenteNaamHoofdletter = gemeenteNaam.substring(0, 1).toUpperCase() + gemeenteNaam.substring(1);

        return new GemeenteDTO(gemeenteRepository.findByNaam(gemeenteNaam).orElseThrow());
        //return gemeenteRepository.vindBuurtenVanGemeente(gemeenteNaamHoofdletter);
    }
}
