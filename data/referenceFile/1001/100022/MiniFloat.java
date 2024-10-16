public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float result = 0.0f;
        float sign = 1.0f;
        float exponent = 0.0f;
        float mantissa = 1.0f;
        // exporesult=2**exponent
        float exporesult = 0.0f;
        // Change bitSequence into char array
        char[] sequence = bitSequence.toCharArray();
        // Identify the sign
        if (sequence[0] == '1') {
            sign = -1.0f;
        }
        // Calculate the exponent
        for (int a = 1; a < 5; a++) {
            if (sequence[a] == '1') {
                exponent += Math.pow(2, 4 - a);
            }
        }
        // Get the exponent part result
        if (sequence[1] == '0') {
            exporesult = (float) Math.pow(2, exponent);
        } else {
            exporesult = (float) Math.pow(2, -1 * (16 - exponent));
        }
        // Calculate the mantissa part
        for (int a = 5; a < sequence.length; a++) {
            if (sequence[a] == '1') {
                mantissa += Math.pow(2, 4 - a);
            }
        }
        // Calculate final result
        result = sign * exporesult * mantissa;
        return result;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int total = 0;
        for (String string : getValidMiniFloatBitSequences()) {
            if (miniFloatFromString(string) == (int) miniFloatFromString(string)) {
                total++;
            }
        }
        return total;
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
