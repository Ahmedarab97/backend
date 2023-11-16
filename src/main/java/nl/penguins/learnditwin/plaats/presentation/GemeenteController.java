package nl.penguins.learnditwin.plaats.presentation;

import nl.penguins.learnditwin.plaats.application.GemeenteService;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gemeente/{gemeenteNaam}")
public class GemeenteController {
    private final GemeenteService gemeenteService;

    public GemeenteController(GemeenteService gemeenteService) {
        this.gemeenteService = gemeenteService;
    }

    @GetMapping("/buurt")
    public ResponseEntity<List<Buurt>> getAlleBuurtenVanGemeente(@PathVariable String gemeenteNaam) {
        return ResponseEntity.ok(this.gemeenteService.getAlleBuurtenVanGemeente(gemeenteNaam));
    }
}
