class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        int index = 0;
        for (int i = 0; i < strSequence.length(); i++) {
            if (strSequence.substring(i, i + 1).compareTo("e") == 0) {
                index = i;
                break;
            }
        }
        exponent = Integer.parseInt(strSequence.substring(index + 1));
        significand = Double.parseDouble(strSequence.substring(0, index));
        // TODO:To parse significand and exponent from strSequence.
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
