package nl.penguins.learnditwin.layers.data;

import nl.penguins.learnditwin.layers.domain.Fitheid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LayerRepository extends JpaRepository<Fitheid, Long> {
}
