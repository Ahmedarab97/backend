package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Gemeente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GemeenteRepository extends MongoRepository<Gemeente, String> {
    Optional<Gemeente> findByNaam(String naam);

    default Gemeente findGemeenteByCode(String regioCode){
        if (regioCode.length() < 6) throw new RuntimeException("Dit is een land en deze worden niet opgeslagen");
        String gemeenteCode = "GM" + regioCode.substring(2, 6);

        return findById(gemeenteCode).orElseThrow();
    }
}
