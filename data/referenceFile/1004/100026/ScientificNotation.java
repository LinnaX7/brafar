public class ScientificNotation {

    // Not to add other method
    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        boolean isPositive = true;
        int beginIndex = 0;
        int dotIndex = -1;
        int eIndex = strSequence.indexOf("e");
        boolean isExponentPositive = true;
        if (strSequence.charAt(0) == '-') {
            isPositive = false;
            beginIndex = 1;
        } else {
            if (strSequence.charAt(0) == '+') {
                beginIndex = 1;
            }
        }
        for (int i = beginIndex; i < eIndex; i++) {
            char curCh = strSequence.charAt(i);
            if (curCh != '.') {
                significand = significand * 10 + (curCh - '0');
            } else {
                dotIndex = i;
            }
        }
        if (dotIndex != -1) {
            for (int i = dotIndex + 1; i < eIndex; i++) {
                significand = significand / ((double) 10);
            }
        }
        if (!isPositive) {
            significand = -significand;
        }
        beginIndex = eIndex + 1;
        if (strSequence.charAt(beginIndex) == '-') {
            isExponentPositive = false;
            beginIndex = beginIndex + 1;
        } else {
            if (strSequence.charAt(beginIndex) == '+') {
                beginIndex = beginIndex + 1;
            }
        }
        for (int i = beginIndex; i < strSequence.length(); i++) {
            char curCh = strSequence.charAt(i);
            exponent = exponent * 10 + (curCh - '0');
        }
        if (!isExponentPositive) {
            exponent = -exponent;
        }
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
