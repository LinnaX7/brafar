public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // Splitting the notation
        String[] arr = strSequence.split("e");
        // converting significand and exponent to double
        significand = Double.parseDouble(arr[0]);
        exponent = (int) Double.parseDouble(arr[1]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
