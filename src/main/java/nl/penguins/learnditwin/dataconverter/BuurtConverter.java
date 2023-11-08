package nl.penguins.learnditwin.dataconverter;

import nl.penguins.learnditwin.plaats.data.BuurtRepository;
import nl.penguins.learnditwin.plaats.data.PlaatsRepository;
import nl.penguins.learnditwin.plaats.domain.Buurt;
import nl.penguins.learnditwin.plaats.domain.Wijk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//@Component
//public class BuurtConverter implements DataConverter {
//    @Autowired
//    BuurtRepository buurtRepository;
//    @Autowired
//    PlaatsRepository plaatsRepository;
//
//    @Override
//    protected void converteerLijnNaarObject(List<String[]> lineParts) {
//        List<Wijk> wijken = new ArrayList<>();
//
//        for (String[] line : lineParts){
//            Buurt buurt = new Buurt(line[0], line[1]);
//
//            Wijk wijk = plaatsRepository.findById(line[6])
//                    .orElseThrow(() -> new RuntimeException("cant find wijk"));
//
//            wijk.addBuurt(buurt.getBuurtCode_id());
//        }
//
//
////
////        wijk.addBuurt(buurt.getBuurtCode_id());
////
////        buurtRepository.save(buurt);
////        plaatsRepository.save(wijk);
//    }
//}
