package nl.penguins.learnditwin.plaats.application.dto;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.Wijk;

@Getter
public class WijkDTO {
    private final String wijkCode;
    private final String naam;
    private final int aantalInwoners;
    private final LocatieInfoDTO wijkInfo;

    public WijkDTO(Wijk wijk) {
        this.wijkCode = wijk.getRegioCode_id().regioCode();
        this.naam = wijk.getNaam();
        this.aantalInwoners = wijk.getAantalInwoners();
        this.wijkInfo = new LocatieInfoDTO(wijk);
    }
}
