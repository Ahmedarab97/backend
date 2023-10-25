package nl.penguins.learnditwin.layers.application;

import nl.penguins.learnditwin.layers.application.DTO.FitheidDTO;
import nl.penguins.learnditwin.layers.data.LayerRepository;
import nl.penguins.learnditwin.layers.domain.Fitheid;
import org.springframework.stereotype.Component;

@Component
public class LayerService {

    private final LayerRepository layerRepository;

    public LayerService(LayerRepository layerRepository) {
        this.layerRepository = layerRepository;
    }

    public FitheidDTO getKleur() {
        Fitheid fitheid = new Fitheid("#010409");
        return FitheidDTO.fromFitheid(fitheid);
    }
}
