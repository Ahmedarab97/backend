package nl.penguins.learnditwin.datatodomainconverter.dataconverterhelpers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DataConverterHelper {
    public static double naarPercentage(double totaalInwoners, double deel){
        double percentageAfgerond = (double) deel / totaalInwoners;
        return afronden(percentageAfgerond);
    }

    public static double afronden(double getal){
        DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        return Double.parseDouble(df.format(getal));
    }
}
