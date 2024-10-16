public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        String[] splitted = strSequence.split("e");
        exponent = Integer.parseInt(splitted[1]);
        significand = Double.parseDouble(splitted[0]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
