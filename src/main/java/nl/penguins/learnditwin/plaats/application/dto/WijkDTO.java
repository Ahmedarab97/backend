package nl.penguins.learnditwin.plaats.application.dto;

import nl.penguins.learnditwin.plaats.domain.Wijk;

public class WijkDTO {
    private final String wijkCode;
    private final String naam;
    private final BuurtInfoDTO wijkInfo;

    public WijkDTO(Wijk wijk) {
        this.wijkCode = wijk.getRegioCode_id();
        this.naam = wijk.getNaam();
        this.wijkInfo = new BuurtInfoDTO(wijk);
    }

    public String getWijkCode() {
        return wijkCode;
    }

    public String getNaam() {
        return naam;
    }

    public BuurtInfoDTO getWijkInfo() {
        return wijkInfo;
    }
}
