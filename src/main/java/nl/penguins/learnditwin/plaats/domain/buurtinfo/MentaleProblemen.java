package nl.penguins.learnditwin.plaats.domain.buurtinfo;

public record MentaleProblemen(double percentageMatigOfHoogRisicoOpAngstOfDepressie,
                               double percentageHoogRisicoOpAngstOfDepressie,
                               double percentageVeelStressInAfgelopen4Weken,
                               double percentageEenzaam,
                               double percentageErnstigOfZeerEenzaam,
                               double percentageEmotioneelEenzaam,
                               double percentageSociaalEenzaam
                               ) {
}
