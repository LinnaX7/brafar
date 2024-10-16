public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        double a;
        int b;
        String[] str = strSequence.split("e", 2);
        a = Double.parseDouble(str[0]);
        b = Integer.parseInt(str[1]);
        significand = significand + a;
        exponent = exponent + b;
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
