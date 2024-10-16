public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        String str = "";
        int exponent = 0;
        double significand = 0.0, retValue;
        for (int i = 0; i < strSequence.length(); i++) {
            char c = strSequence.charAt(i);
            if (c != 'e') {
                str = str + c;
            } else {
                significand = Double.parseDouble(str);
                str = "";
            }
        }
        exponent = Integer.parseInt(str);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
