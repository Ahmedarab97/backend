package nl.penguins.learnditwin.plaats.application;

import nl.penguins.learnditwin.plaats.data.GemeenteRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaatsService {
    private final GemeenteRepository gemeenteRepository;

    public PlaatsService(GemeenteRepository gemeenteRepository) {
        this.gemeenteRepository = gemeenteRepository;
    }

    public List<Buurt> getAlleBuurtenVanGemeente(String gemeenteNaam) {
        return gemeenteRepository.vindBuurtenVanGemeente("Nieuwegein");
    }
}
