package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaatsRepository extends MongoRepository<Wijk, String> {
}
