import java.util.Objects;

public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        StringBuilder world = new StringBuilder();
        String[] str = strSequence.split("");
        // significand = str[0] == "-" ? -Integer.parseInt(str[1]) : Integer.parseInt(str[0]);
        int a = 0;
        String x = "";
        while (!Objects.equals(str[a], "e")) {
            x += str[a];
            a++;
        }
        significand = Double.parseDouble(x);
        String hello = "";
        for (int i = 0; i < str.length; i++) {
            if (Objects.equals(str[i], "e")) {
                for (int j = i + 1; j < str.length; j++) {
                    hello += str[j];
                }
                exponent = Integer.parseInt(hello);
            }
        }
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
