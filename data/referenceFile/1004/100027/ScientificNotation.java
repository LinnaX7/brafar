public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        exponent = Integer.parseInt(strSequence.split("e")[1]);
        significand = Double.parseDouble(strSequence.split("e")[0]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
