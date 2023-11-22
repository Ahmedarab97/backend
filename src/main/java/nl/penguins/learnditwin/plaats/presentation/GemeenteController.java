package nl.penguins.learnditwin.plaats.presentation;

import nl.penguins.learnditwin.plaats.application.GemeenteService;
import nl.penguins.learnditwin.plaats.application.dto.GemeenteDTO;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gemeente/{gemeenteNaam}")
public class GemeenteController {
    private final GemeenteService gemeenteService;

    public GemeenteController(GemeenteService gemeenteService) {
        this.gemeenteService = gemeenteService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5173")
    @GetMapping
    public ResponseEntity<GemeenteDTO> getGemeente(@PathVariable String gemeenteNaam) {
        return ResponseEntity.ok(this.gemeenteService.getAlleBuurtenVanGemeente(gemeenteNaam));
    }
}
