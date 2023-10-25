package nl.penguins.learnditwin.info.domain;

import jakarta.persistence.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

//@Entity
public class Verband <T> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String verbandUitleg;
    private T objectTeVergelijken;
    private T gemiddeldeObjecten;

    public Verband(String attribuutTeVergelijken, T objectTeVergelijken, T gemiddeldeObjecten) {
        this.objectTeVergelijken = objectTeVergelijken;
        this.gemiddeldeObjecten = gemiddeldeObjecten;
        this.verbandUitleg = genereerVerband(attribuutTeVergelijken);
    }

    protected Verband() {

    }

    public static <T> Verband<T> genereerVerband(String attribuut, T objectToCompare, T averageObject) {
        return new Verband<>(attribuut, objectToCompare, averageObject);
    }

    public String genereerVerband(String attribuutTeVergelijken){
        String verband = "geen verband gevonden";

        try {
            Method[] methods = objectTeVergelijken.getClass().getMethods();

            Set<Method> getterMethods = getMethods(attribuutTeVergelijken, methods);

            Method getAttribuut = objectTeVergelijken.getClass().getMethod("get" + attribuutTeVergelijken);

            int strijgingPercentageAttribuut = berekenStijgingPercentageVanGetterWaarde(getAttribuut);
            boolean vergelijkAttribuutIsToegenomen = strijgingPercentageAttribuut >= 0;

            int kleinsteVerschil = 1000000000;

            for (Method method : getterMethods){
                String attribuutVanMethod = method.getName().substring(3);

                int stijgingPercentage = berekenStijgingPercentageVanGetterWaarde(method);

                int percentageVerschil = Math.abs(Math.abs(stijgingPercentage) - Math.abs(strijgingPercentageAttribuut));
                boolean attribuutIsToegenomen = stijgingPercentage >= 0;

                if(percentageVerschil <= 5 && percentageVerschil < kleinsteVerschil){
                    kleinsteVerschil = percentageVerschil;

                    if(vergelijkAttribuutIsToegenomen && attribuutIsToegenomen){
                        verband = String.format("%s en %s hadden ongeveer een gelijke stijging meegemaakt", attribuutTeVergelijken, attribuutVanMethod);
                    } else if(vergelijkAttribuutIsToegenomen && !attribuutIsToegenomen){
                        verband = String.format("Toen de %s toenam, vertoonde %s een evenredige afname in dezelfde mate.", attribuutTeVergelijken, attribuutVanMethod);
                    } else if(!vergelijkAttribuutIsToegenomen && attribuutIsToegenomen){
                        verband = String.format("Toen de %s afnam, vertoonde %s een evenredige toename in dezelfde mate.", attribuutTeVergelijken, attribuutVanMethod);
                    }else if(!vergelijkAttribuutIsToegenomen && !attribuutIsToegenomen){
                        verband = String.format("%s en %s hadden ongeveer een gelijke afname meegemaakt.", attribuutTeVergelijken, attribuutVanMethod);
                    }
                }
            }

            return verband;
        } catch (Exception e) {
            return verband;
        }
    }

    private static Set<Method> getMethods(String attribuutTeVergelijken, Method[] methods) {
        return Arrays.stream(methods)
                .filter(method -> Modifier.isPublic(method.getModifiers()) &&
                        method.getParameterCount() == 0 &&
                        method.getName().startsWith("get") &&
                        !method.getName().endsWith(attribuutTeVergelijken) &&
                        !method.getName().endsWith("Id") &&
                        (method.getReturnType() == int.class || method.getReturnType() == float.class))
                .collect(Collectors.toSet());
    }

    private int berekenStijgingPercentageVanGetterWaarde(Method method) throws InvocationTargetException, IllegalAccessException {
        int waardeTeVergelijken = (int) method.invoke(objectTeVergelijken);
        int gemiddeldeWaarde = (int) method.invoke(gemiddeldeObjecten);

        double stijgingspercentage = (((double) waardeTeVergelijken - gemiddeldeWaarde) / gemiddeldeWaarde) * 100;
        return  (int) Math.round(stijgingspercentage);
    }

    public String getVerbandUitleg() {
        return verbandUitleg;
    }
}
