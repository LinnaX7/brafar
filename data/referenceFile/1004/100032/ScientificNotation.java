public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        significand = Double.parseDouble(strSequence.substring(0, strSequence.indexOf("e")));
        exponent = Integer.parseInt(strSequence.substring(strSequence.indexOf("e") + 1));
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
