package nl.penguins.learnditwin.plaats.application.dto;

import nl.penguins.learnditwin.plaats.domain.Gemeente;

import java.util.Set;
import java.util.stream.Collectors;

public class GemeenteDTO {
    private String gemeenteCode;
    private String naam;

    private Set<WijkDTO> wijken;

    public GemeenteDTO(Gemeente gemeente) {
        this.gemeenteCode = gemeente.getRegioCode_id();
        this.naam = gemeente.getNaam();
        this.wijken = gemeente.getWijken().stream()
                .map(WijkDTO::new)
                .collect(Collectors.toSet());
    }

    public String getGemeenteCode() {
        return gemeenteCode;
    }

    public String getNaam() {
        return naam;
    }

    public Set<WijkDTO> getWijken() {
        return wijken;
    }
}
