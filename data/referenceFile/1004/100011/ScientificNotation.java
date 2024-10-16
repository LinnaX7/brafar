public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // divide string into 2 parts->exp & sign
        String[] array = strSequence.split("e", 2);
        exponent = Integer.parseInt(array[1]);
        significand = Double.valueOf(array[0]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
