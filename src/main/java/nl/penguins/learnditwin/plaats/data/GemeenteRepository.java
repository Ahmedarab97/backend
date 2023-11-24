package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Gemeente;
import nl.penguins.learnditwin.plaats.domain.ids.RegioCode;
import nl.penguins.learnditwin.plaats.exception.GemeenteNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GemeenteRepository extends MongoRepository<Gemeente, RegioCode> {
    Optional<Gemeente> findByNaam(String naam);

    default Gemeente findGemeenteByCode(RegioCode regioCode) {
        RegioCode gemeenteCode = regioCode.getGemeenteCode();

        return findById(gemeenteCode)
                .orElseThrow(() -> new GemeenteNotFoundException
                        (String.format("Gemeente met regioCode %s niet gevonden", gemeenteCode)));
    }
}
