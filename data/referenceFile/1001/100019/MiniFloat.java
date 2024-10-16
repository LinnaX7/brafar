public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float mp = 1;
        mp = mp / 2;
        float mantissa = 1;
        float exponent = 0;
        float power = 4;
        float miniFloat = 0;
        for (int i = 5; i < 8; i++) {
            if (bitSequence.charAt(i) == '1') {
                mantissa = mantissa + mp;
            }
            mp = mp / 2;
        }
        if (bitSequence.charAt(1) == '1') {
            exponent = -8;
        }
        for (int i = 2; i < 5; i++) {
            if (bitSequence.charAt(i) == '1') {
                exponent = exponent + power;
            }
            power = power / 2;
        }
        miniFloat = mantissa * (float) Math.pow(2, exponent);
        if (bitSequence.charAt(0) == '1') {
            miniFloat = miniFloat * -1;
        }
        return miniFloat;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int counter = 0;
        for (int i = 0; i < (int) Math.pow(2, MINI_FLOAT_SIZE); i++) {
            float num = miniFloatFromString(getValidMiniFloatBitSequences()[i]);
            if (num % 1 == 0) {
                counter = counter + 1;
            }
        }
        return counter;
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
