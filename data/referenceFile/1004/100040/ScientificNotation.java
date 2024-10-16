public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        // check the length
        int length = strSequence.length();
        // for store significand
        String beforeE = "";
        // for store exponent
        String afterE = "";
        int i = 0;
        int index = 0;
        while (!(strSequence.charAt(i) == 'e')) {
            beforeE += strSequence.charAt(i);
            index = i;
            i++;
        }
        index++;
        if (strSequence.charAt(index) == 'e') {
            index++;
            for (int j = index; j < length; j++) {
                afterE += strSequence.charAt(j);
            }
        }
        // reset index to 0
        index = 0;
        // significand = Double.parseDouble(temp);
        // convert String temp to double
        // exponent = Integer.parseInt(afterE);
        boolean isNeg = false;
        boolean eIsNeg = false;
        int dotLocation = 0, eLocation = 0;
        double dot = 1, numBeforeDot = 0, numAfterDot = 0;
        int numAfterE = 0;
        // String temp = "";
        if (strSequence.charAt(index) == '-') {
            // if is negative number, mark isNeg to true and move index to next
            isNeg = true;
            index++;
        }
        for (int k = index; k < length; k++) {
            // go through each element
            if (strSequence.charAt(k) >= '0' && strSequence.charAt(k) <= '9') {
                if (eLocation == 0) {
                    if (dotLocation == 0) {
                        numBeforeDot = numBeforeDot * 10 + (strSequence.charAt(k) - 48);
                    } else {
                        dot *= 0.1;
                        numAfterDot = numAfterDot + (strSequence.charAt(k) - 48) * dot;
                    }
                } else {
                    numAfterE = numAfterE * 10 + (strSequence.charAt(k) - 48);
                }
            } else {
                if (strSequence.charAt(k) == '.') {
                    dotLocation++;
                } else {
                    if (strSequence.charAt(k) == 'e' || strSequence.charAt(k) == 'E') {
                        eLocation++;
                    } else {
                        if (strSequence.charAt(k) == '-') {
                            eIsNeg = true;
                        }
                    }
                }
            }
        }
        if (// if exponent is negative, then exponent number * -1
        eIsNeg) {
            numAfterE *= (-1);
        }
        exponent = numAfterE;
        if (// if number is negative, then * -1
        !isNeg) {
            significand = numBeforeDot + numAfterDot;
        } else {
            significand = (numBeforeDot + numAfterDot) * (-1);
        }
        // TODO:To parse significand and exponent from strSequence.
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
