package nl.penguins.learnditwin.plaats.application.dto;

import lombok.Getter;
import nl.penguins.learnditwin.plaats.domain.Wijk;

@Getter
public class WijkDTO {
    private final String wijkCode;
    private final String naam;
    private final BuurtInfoDTO wijkInfo;

    public WijkDTO(Wijk wijk) {
        this.wijkCode = wijk.getRegioCode_id();
        this.naam = wijk.getNaam();
        this.wijkInfo = new BuurtInfoDTO(wijk);
    }
}
