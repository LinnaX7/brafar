public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0, retValue;
        String[] word = strSequence.split("e");
        significand = Double.parseDouble(word[0]);
        exponent = Integer.parseInt(word[1]);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
