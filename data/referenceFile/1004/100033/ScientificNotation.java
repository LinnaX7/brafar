import java.lang.Math;

public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        String[] temp = strSequence.split("e");
        significand = Double.parseDouble(temp[0]);
        exponent = Integer.parseInt(temp[1]);
        // TODO:To parse significand and exponent from strSequence.
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
