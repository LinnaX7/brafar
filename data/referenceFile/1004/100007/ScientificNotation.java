public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        int index = strSequence.indexOf('e');
        significand = Double.parseDouble(strSequence.substring(0, index));
        exponent = Integer.parseInt(strSequence.substring(index + 1, strSequence.length()));
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
