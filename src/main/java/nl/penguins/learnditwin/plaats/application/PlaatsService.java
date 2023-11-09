package nl.penguins.learnditwin.plaats.application;

import nl.penguins.learnditwin.plaats.data.PlaatsRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class PlaatsService {
    private final PlaatsRepository repository;

    @Autowired
    private MongoTemplate mongo;

    public PlaatsService(PlaatsRepository repository) {
        this.repository = repository;
    }

    public List<Wijk> getAlleWijken() {
        return this.mongo.findAll(Wijk.class).stream().toList();
    }

    public void saveWijk(String naam, String postcode4, Set<Buurt> buurten) {
        Wijk wijk =  Wijk.generateWijk(naam, postcode4, buurten);
        mongo.save(wijk);
    }
}
