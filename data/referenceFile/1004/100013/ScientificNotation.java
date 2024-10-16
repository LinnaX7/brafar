public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        int index = strSequence.indexOf("e");
        String sig = strSequence.substring(0, index);
        String eValue = strSequence.substring(index + 1);
        significand = Double.parseDouble(sig);
        exponent = Integer.parseInt(eValue);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
