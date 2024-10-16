public class ScientificNotation {

    // TODO:To parse significand and exponent from strSequence.
    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // my code
        int eValue = strSequence.indexOf("e");
        // convert to double
        String value = strSequence.substring(0, eValue);
        significand = Double.parseDouble(value);
        // extract exponent
        // convert exponent value to int
        exponent = Integer.parseInt(strSequence.substring(eValue + 1));
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
