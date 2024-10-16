public class ScientificNotation {

    // TODO:To parse significand and exponent from strSequence.
    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        String tempSignificand = "";
        String tempExponent = "";
        for (int i = 0; i < strSequence.indexOf("e"); i++) {
            tempSignificand = tempSignificand + strSequence.charAt(i);
        }
        for (int i = strSequence.indexOf("e") + 1; i < strSequence.length(); i++) {
            tempExponent = tempExponent + strSequence.charAt(i);
        }
        significand = Double.parseDouble(tempSignificand);
        exponent = Integer.parseInt(tempExponent);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
