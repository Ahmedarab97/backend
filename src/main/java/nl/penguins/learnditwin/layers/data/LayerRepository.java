package nl.penguins.learnditwin.layers.data;

import nl.penguins.learnditwin.layers.domain.Fitheid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LayerRepository extends MongoRepository<Fitheid, Long> {
}
