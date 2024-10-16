public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        String[] temp = strSequence.split("e");
        significand = Double.parseDouble(temp[0]);
        exponent = Integer.parseInt(temp[1]);
        retValue = significand * Math.pow(10, exponent);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
