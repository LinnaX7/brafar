public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        int pos1 = strSequence.length();
        int pos2 = 0;
        for (int j = 0; j < pos1; j++) {
            if (strSequence.substring(j, j + 1).compareTo("e") == 0) {
                pos2 = j;
            }
        }
        String x = strSequence.substring(0, pos2);
        String y = strSequence.substring(pos2 + 1, pos1);
        significand = Double.parseDouble(x);
        exponent = Integer.parseInt(y);
        // TODO:To parse significand and exponent from strSequence.
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
