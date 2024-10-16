public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // TODO:To parse significand and exponent from strSequence.
        String[] str = new String[2];
        str = strSequence.split("e");
        exponent = Integer.parseInt(str[1]);
        significand = Double.parseDouble(str[0]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
