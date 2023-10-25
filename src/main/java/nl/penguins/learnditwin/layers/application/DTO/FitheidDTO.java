package nl.penguins.learnditwin.layers.application.DTO;

import nl.penguins.learnditwin.layers.domain.Fitheid;

public record FitheidDTO(String kleur) {

    public static FitheidDTO fromFitheid(Fitheid fitheid) {
        return new FitheidDTO(
          fitheid.getKleur()
        );
    }
}
