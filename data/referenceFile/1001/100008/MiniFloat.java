public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static void main(String[] arg) {
        // Test the numIntegralMiniFloats() method;
        numIntegralMiniFloats();
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int sign = Integer.valueOf(bitSequence.charAt(0) - 48);
        int exponent = 0;
        float mantissa = 0;
        // Find exponent;
        for (int i = 4; i >= 2; i--) {
            int a = 4 - i;
            exponent = exponent + ((Integer.valueOf(bitSequence.charAt(i)) - 48) * (int) Math.pow(2, a));
        }
        if (Integer.valueOf(bitSequence.charAt(1)) - 48 == 1) {
            exponent = exponent - 8;
        }
        // Find mantissa;
        for (int j = 7; j >= 5; j--) {
            int b = 4 - j;
            mantissa = mantissa + ((Integer.valueOf(bitSequence.charAt(j)) - 48) * (float) Math.pow(2, b));
        }
        // Find the miniFloat;
        float miniFloat = ((1 + mantissa) * (float) Math.pow(2, exponent));
        // Define the sign of miniFloat;
        if (sign == 0) {
            miniFloat = miniFloat;
        } else {
            miniFloat = -miniFloat;
        }
        return miniFloat;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] bitSequence = getValidMiniFloatBitSequences();
        int c = 0;
        int number = 1;
        int bitValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        // Find the bitSequence and the corresponding miniFloat;
        for (int i = 0; i < bitValues; i++) {
            float miniFloat = miniFloatFromString(bitSequence[i]);
            if ((miniFloat == (int) (miniFloat)) && (miniFloat != 0)) {
                System.out.println(number + ": " + bitSequence[i] + " = " + miniFloat);
                number++;
                c++;
            }
        }
        System.out.println("Number of all integral miniFloat values: " + c);
        return 0;
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
