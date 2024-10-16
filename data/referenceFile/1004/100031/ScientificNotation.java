public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        double a = Double.parseDouble(strSequence);
        if (a < 10) {
            significand = a;
        } else {
            if (a >= 10) {
                significand = a / Math.pow(10, exponent);
            }
        }
        // TODO:To parse significand and exponent from strSequence.
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
