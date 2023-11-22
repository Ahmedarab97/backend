package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WijkRepository extends MongoRepository<Wijk, String> {
    default Optional<Buurt> findBuurtByWijkAndBuurtNaam(String wijkNaam, String buurtNaam) {
        Optional<Wijk> wijkOptional = findByNaam(wijkNaam);
        return wijkOptional.flatMap(wijk -> wijk.getBuurten().stream()
                .filter(buurt -> buurt.getNaam().equals(buurtNaam))
                .findFirst());
    }

    Optional<Wijk> findByNaam(String naam);
}
