public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // get sign number
        int signValue;
        int sign = Integer.parseInt(bitSequence.substring(0, 1));
        if (sign == 0) {
            signValue = 1;
        } else {
            signValue = -1;
        }
        // get sign and 2's complement of exponent
        String binaryExponent = bitSequence.substring(2, 5);
        int exponent = 0;
        for (int i = 1; i <= 3; i++) {
            int num = Integer.parseInt(binaryExponent.substring(i - 1, i));
            exponent += (int) Math.pow(2, 3 - i) * num;
        }
        int n = Integer.parseInt(bitSequence.substring(1, 2));
        if (n == 1) {
            exponent -= 8;
        }
        // get significand number
        String binarySignificand = bitSequence.substring(5, 8);
        float significand = 0.0F;
        for (int i = 1; i <= 3; i++) {
            float num = Integer.parseInt(binarySignificand.substring(i - 1, i));
            significand += (float) Math.pow(2, -i) * num;
        }
        // get miniFloat
        return signValue * (significand + 1) * (float) Math.pow(2, exponent);
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int number = 0;
        // if float equals to int, it's integral
        for (String s : getValidMiniFloatBitSequences()) {
            if (miniFloatFromString(s) == (int) miniFloatFromString(s)) {
                number++;
            }
        }
        return number;
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
