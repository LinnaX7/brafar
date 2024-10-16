public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // Extracting the first number ('0'/'1') of bitSequence to judge weather it's positive or negative 48 is 0's ASCII code
        int sign = bitSequence.charAt(0) == 48 ? 1 : -1;
        // Extracting the exponent and convert it(2's complement) into decimal number
        int exponent = Integer.parseInt(bitSequence.substring(2, 5), 2);
        // If the exponent is negative, then use the radix-minus-one complement, 49 is 1's ASCII code
        if (bitSequence.charAt(1) == 49) {
            exponent -= 8;
        }
        // Extracting the significand and convert it into decimal number
        int pre_significand = Integer.parseInt('1' + bitSequence.substring(5, 8), 2);
        float significand = pre_significand / (float) (2 * 2 * 2);
        // Calculating and return the value of the bitSequence
        return sign * significand * (float) Math.pow(2, exponent);
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        // Init the counter
        int counter = 0;
        // Get all valid bit sequences
        String[] allSequence = getValidMiniFloatBitSequences();
        // Counting the integral miniFloat values
        for (String s : allSequence) {
            if (miniFloatFromString(s) % 1 == 0)
                counter++;
        }
        // return the number of integral miniFloat values
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
