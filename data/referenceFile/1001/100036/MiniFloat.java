public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float exponent = 0;
        float significand = 1;
        float minfloat;
        if (bitSequence.charAt(1) == '1') {
            exponent = -8;
        }
        if (bitSequence.charAt(2) == '1') {
            exponent = exponent + (float) Math.pow(2, 2);
        }
        if (bitSequence.charAt(3) == '1') {
            exponent = exponent + (float) Math.pow(2, 1);
        }
        if (bitSequence.charAt(4) == '1') {
            exponent = exponent + (float) Math.pow(2, 0);
        }
        if (bitSequence.charAt(5) == '1') {
            significand = significand + (float) Math.pow(2, -1);
        }
        if (bitSequence.charAt(6) == '1') {
            significand = significand + (float) Math.pow(2, -2);
        }
        if (bitSequence.charAt(7) == '1') {
            significand = significand + (float) Math.pow(2, -3);
        }
        if (bitSequence.charAt(0) == '0') {
            minfloat = significand * (float) Math.pow(2, exponent);
        } else {
            minfloat = -significand * (float) Math.pow(2, exponent);
        }
        return minfloat;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int count = 0;
        for (int i = 0; i < (int) Math.pow(2, MINI_FLOAT_SIZE); i++) {
            float num = miniFloatFromString(getValidMiniFloatBitSequences()[i]);
            if (num % 1 == 0) {
                count = count + 1;
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
}
