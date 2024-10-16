public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        String[] split = strSequence.split("e");
        significand = Double.parseDouble(split[0]);
        exponent = Integer.parseInt(split[1]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
