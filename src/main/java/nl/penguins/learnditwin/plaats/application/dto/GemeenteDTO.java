package nl.penguins.learnditwin.plaats.application.dto;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.Gemeente;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class GemeenteDTO {
    private final String gemeenteCode;
    private final String naam;
    private final int aantalInwoners;

    private final Set<WijkDTO> wijken;

    public GemeenteDTO(Gemeente gemeente) {
        this.gemeenteCode = gemeente.getRegioCode_id().getRegioCode();
        this.naam = gemeente.getNaam();
        this.aantalInwoners = gemeente.getAantalInwoners();
        this.wijken = gemeente.getWijken().stream()
                .map(WijkDTO::new)
                .collect(Collectors.toSet());
    }
}
