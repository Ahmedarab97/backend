package nl.penguins.learnditwin.plaats.data;

import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Gemeente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GemeenteRepository extends MongoRepository<Gemeente, String> {
    Optional<Gemeente> findByNaam(String naam);
    default List<Buurt> vindBuurtenVanGemeente(String gemeenteNaam){
        Optional<Gemeente> gemeentes = findByNaam(gemeenteNaam);

        return gemeentes.stream()
                .flatMap(gemeente -> gemeente.getWijken().stream())
                .flatMap(wijk -> wijk.getBuurten().stream())
                .toList();
    }
}
