package nl.penguins.learnditwin.plaats.presentation;

import nl.penguins.learnditwin.plaats.application.PlaatsService;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gemeente/{gemeenteNaam}")
public class PlaatsController {
    private final PlaatsService plaatsService;

    public PlaatsController(PlaatsService plaatsService) {
        this.plaatsService = plaatsService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @GetMapping("/buurt")
    public ResponseEntity<List<Buurt>> getAlleBuurtenVanGemeente(@PathVariable String gemeenteNaam) {
        return ResponseEntity.ok(this.plaatsService.getAlleBuurtenVanGemeente(gemeenteNaam));
    }
}
