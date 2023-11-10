package nl.penguins.learnditwin.layers.application;

import nl.penguins.learnditwin.layers.application.DTO.FitheidDTO;
import nl.penguins.learnditwin.layers.application.LayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/layer")
public class LayerController {
    private final LayerService layerService;

    public LayerController(LayerService layerService) {
        this.layerService = layerService;
    }


    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @GetMapping("/fitheid")
    public ResponseEntity<FitheidDTO> getFitheid() {
        FitheidDTO fitheidDTO = this.layerService.getKleur();
        return ResponseEntity.ok(fitheidDTO);
    }
}
