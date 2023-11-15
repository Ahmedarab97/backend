package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Gemeente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GemeenteRepository extends MongoRepository<Gemeente, String> {
    Optional<Gemeente> findByNaam(String naam);
}
