package nl.penguins.learnditwin.dataconverter;

import nl.penguins.learnditwin.plaats.data.PlaatsRepository;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
//public class WijkConverter extends DataConverter{
//    @Autowired
//    PlaatsRepository plaatsRepository;
//
//    @Override
//    protected void converteerLijnNaarObject(List<String[]> lineParts) {
////        Wijk wijk = new Wijk(lineParts[0], lineParts[1]);
////        plaatsRepository.save(wijk);
//    }
//}
