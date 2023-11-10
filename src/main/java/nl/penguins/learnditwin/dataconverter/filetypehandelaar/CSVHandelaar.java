//package nl.penguins.learnditwin.dataconverter.filehandelaar;
//
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.List;
//import java.util.stream.Collectors;
//
////@Component
//public class CSVHandelaar implements FileHandelaar{
//    @Override
//    public List<String[]> readData(String path) {
//        try {
//            return Files.lines(Paths.get(path))
//                    .map(line -> line.split(";"))
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new RuntimeException("Error reading CSV file: " + path, e);
//        }
//    }
//}
