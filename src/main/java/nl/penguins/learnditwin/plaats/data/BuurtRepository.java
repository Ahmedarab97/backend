package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Buurt;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuurtRepository extends MongoRepository<Buurt, String> {

}

