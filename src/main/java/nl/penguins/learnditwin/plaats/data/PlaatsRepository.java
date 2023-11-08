package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaatsRepository extends MongoRepository<Wijk, String> {
}
