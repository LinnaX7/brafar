public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        String[] parts = strSequence.split("e");
        significand = Double.parseDouble(parts[0]);
        exponent = Integer.parseInt(parts[1]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
