public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int exponent = 0;
        int ex = 4;
        for (int i = 2; i <= 4; i++) {
            if (bitSequence.charAt(i) == '1') {
                exponent += ex;
            }
            ex /= 2;
        }
        if (bitSequence.charAt(1) == '1') {
            exponent -= 8;
        }
        double x = 0.5;
        float mantissa = 1;
        for (int i = 5; i <= 7; i++) {
            if (bitSequence.charAt(i) == '1') {
                mantissa += x;
            }
            x /= 2;
        }
        if (exponent >= 0) {
            for (int i = 1; i <= exponent; i++) {
                mantissa *= 2;
            }
        } else {
            for (int i = -1; i >= exponent; i--) {
                mantissa /= 2;
            }
        }
        if (bitSequence.charAt(0) == '1') {
            mantissa = -mantissa;
        }
        return mantissa;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] validMiniFloatBitSequences = getValidMiniFloatBitSequences();
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        int count = 0;
        for (int i = 0; i < nbrValues; i++) {
            float miniFloat = miniFloatFromString(validMiniFloatBitSequences[i]);
            if (Math.abs(miniFloat - (int) miniFloat) < 1e-6f)
                count++;
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
}
