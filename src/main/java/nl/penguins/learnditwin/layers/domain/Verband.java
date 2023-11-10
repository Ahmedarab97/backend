package nl.penguins.learnditwin.layers.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Verband <T> {
    private String verbandUitleg;
    private T objectTeVergelijken;
    private T gemiddeldeObjecten;

    public Verband(String attribuutTeVergelijken, T objectTeVergelijken, T gemiddeldeObjecten) {
        this.objectTeVergelijken = objectTeVergelijken;
        this.gemiddeldeObjecten = gemiddeldeObjecten;
        this.verbandUitleg = genereerVerband(attribuutTeVergelijken);
    }

    public Verband(String attribuutTeVergelijken, String attribuutTeVergelijkenMet, T objectTeVergelijken, T gemiddeldeObjecten) {
        this.objectTeVergelijken = objectTeVergelijken;
        this.gemiddeldeObjecten = gemiddeldeObjecten;
        this.verbandUitleg = genereerVerbandTussen(attribuutTeVergelijken, attribuutTeVergelijkenMet);
    }

    protected Verband() {

    }

    public static <T> Verband<T> genereerVerband(String attribuut, T objectToCompare, T averageObject) {
        return new Verband<>(attribuut, objectToCompare, averageObject);
    }

    public static <T> Verband<T> genereerVerband(String attribuut, String attribuutVergelijkenMet, T objectToCompare, T averageObject) {
        return new Verband<>(attribuut, objectToCompare, averageObject);
    }

    public String genereerVerbandTussen(String attribuutTeVergelijken, String attribuutVergelijkenMet){
        String verband = "geen verband gevonden";

        try {
            Method getAttribuut = objectTeVergelijken.getClass().getMethod("get" + attribuutTeVergelijken);
            Method getAttribuutVergelijkenMet = objectTeVergelijken.getClass().getMethod("get" + attribuutVergelijkenMet);

            int stijgingPercentageAttribuut = berekenStijgingPercentageVanGetterWaarde(getAttribuut);
            int stijgingPercentage = berekenStijgingPercentageVanGetterWaarde(getAttribuutVergelijkenMet);

            boolean vergelijkAttribuutIsToegenomen = stijgingPercentageAttribuut >= 0;
            boolean attribuutIsToegenomen = stijgingPercentage >= 0;

            int percentageVerschil = Math.abs(Math.abs(stijgingPercentage) - Math.abs(stijgingPercentageAttribuut));

            if(percentageVerschil <= 5){
                if(vergelijkAttribuutIsToegenomen && attribuutIsToegenomen){
                    verband = String.format("%s en %s hadden ongeveer een gelijke stijging meegemaakt", attribuutTeVergelijken, attribuutVergelijkenMet);
                } else if(vergelijkAttribuutIsToegenomen && !attribuutIsToegenomen){
                    verband = String.format("Toen de %s toenam, vertoonde %s een evenredige afname in dezelfde mate.", attribuutTeVergelijken, attribuutVergelijkenMet);
                } else if(!vergelijkAttribuutIsToegenomen && attribuutIsToegenomen){
                    verband = String.format("Toen de %s afnam, vertoonde %s een evenredige toename in dezelfde mate.", attribuutTeVergelijken, attribuutVergelijkenMet);
                }else if(!vergelijkAttribuutIsToegenomen && !attribuutIsToegenomen){
                    verband = String.format("%s en %s hadden ongeveer een gelijke afname meegemaakt.", attribuutTeVergelijken, attribuutVergelijkenMet);
                }
            }

            return verband;
        } catch (Exception e) {
            return verband;
        }
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
