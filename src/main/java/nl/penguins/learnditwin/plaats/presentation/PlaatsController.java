package nl.penguins.learnditwin.plaats.presentation;

import nl.penguins.learnditwin.plaats.application.PlaatsService;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlaatsController {
    private final PlaatsService plaatsService;

    public PlaatsController(PlaatsService plaatsService) {
        this.plaatsService = plaatsService;
    }

    @GetMapping("/nieuwegein")
    public ResponseEntity<List<Wijk>> getAlleWijkenDataNieuwegein() {
        return ResponseEntity.ok(this.plaatsService.getAlleWijken());
    }
}
