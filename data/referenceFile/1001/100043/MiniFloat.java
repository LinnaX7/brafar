public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        if (bitSequence.length() != 8) {
            return 0;
        }
        for (int counter = 0; counter < bitSequence.length(); counter++) {
            if (bitSequence.charAt(counter) != '0' && bitSequence.charAt(counter) != '1') {
                return 0;
            }
        }
        float value, exp = 0.0f, exponent = 1.0f, significand = 1.0f;
        for (int i = 4, bit = 0; i > 1; i--, bit++) {
            // find exp
            if (bitSequence.charAt(i) != '0') {
                float digit = 1.0f;
                for (int j = 0; j < bit; j++) {
                    digit *= 2;
                }
                exp += digit;
            }
        }
        for (int k = 5, bits = 1; k < bitSequence.length(); k++, bits++) {
            // find significand(mantissa)
            if (bitSequence.charAt(k) != '0') {
                float digits = 1.0f;
                for (int l = 0; l < bits; l++) {
                    digits *= 0.5;
                }
                significand += digits;
            }
        }
        if (bitSequence.charAt(1) == '1') {
            float firstDigit = -1.0f;
            for (int c = 0; c < 3; c++) {
                firstDigit *= 2;
            }
            exp += firstDigit;
        }
        if (exp > 0) {
            for (int a = 0; a < exp; a++) {
                // find 2^exp
                exponent *= 2;
            }
        } else {
            if (exp < 0) {
                for (int b = 0; b < (-1 * exp); b++) {
                    exponent *= 0.5;
                }
            }
        }
        value = significand * exponent;
        value = (bitSequence.charAt(0) == '0') ? (value * 1) : (value * (-1));
        return value;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            float value = miniFloatFromString(s);
            if ((value - (int) value < (1E-6f)) && (value - (int) value > -(1E-6f))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get all valid bit sequences for miniFloat values.
     */
    private static String[] getValidMiniFloatBitSequences() {
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return result;
    }

    private static int MINI_FLOAT_SIZE = 8;
}
