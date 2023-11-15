package nl.penguins.learnditwin.plaats.presentation;

import nl.penguins.learnditwin.plaats.application.PlaatsService;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gemeente/{gemeenteNaam}")
public class PlaatsController {
    private final PlaatsService plaatsService;

    public PlaatsController(PlaatsService plaatsService) {
        this.plaatsService = plaatsService;
    }

    @GetMapping("/buurt")
    public ResponseEntity<List<Buurt>> getAlleBuurtenVanGemeente(@PathVariable String gemeenteNaam) {
        return ResponseEntity.ok(this.plaatsService.getAlleBuurtenVanGemeente(gemeenteNaam));
    }
}
