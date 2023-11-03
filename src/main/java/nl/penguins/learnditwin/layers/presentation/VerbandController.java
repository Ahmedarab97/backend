package nl.penguins.learnditwin.layers.presentation;

import nl.penguins.learnditwin.layers.application.VerbandDTO;
import nl.penguins.learnditwin.layers.application.VerbandInformatieDTO;
import nl.penguins.learnditwin.layers.application.VerbandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("verband")
public class VerbandController {
    private final VerbandService verbandService;

    public VerbandController(VerbandService verbandService) {
        this.verbandService = verbandService;
    }

    @PostMapping
    public String genereerVerband(@RequestBody VerbandDTO verbandDTO) {
        return verbandService.getVerband(verbandDTO.attribuutTeVergelijken(), verbandDTO.optioneelMetTeVergelijken(), verbandDTO.idTeVergelijken(), verbandDTO.idObjectenGemiddelde());
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:5173")
    public VerbandInformatieDTO genereerVerband() {
        return new VerbandInformatieDTO(verbandService.getVerband());
    }
}
