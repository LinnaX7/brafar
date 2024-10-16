public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        double result = 0;
        double mantissa = 0;
        double exp = 0;
        if (bitSequence.charAt(1) == '0') {
            for (int i = 2; i < 5; i++) {
                char temp1 = bitSequence.charAt(i);
                if (temp1 == '1') {
                    exp = Math.pow(2, (4 - i)) + exp;
                }
            }
        }
        if (bitSequence.charAt(1) == '1') {
            for (int i = 2; i < 5; i++) {
                char temp1 = bitSequence.charAt(i);
                if (temp1 == '0') {
                    exp = Math.pow(2, (4 - i)) + exp;
                }
            }
            exp = (-1) * exp - 1;
        }
        for (int i = 5; i < 8; i++) {
            char temp2 = bitSequence.charAt(i);
            if (temp2 == '1') {
                if (i == 5) {
                    mantissa = mantissa + 4;
                }
                if (i == 6) {
                    mantissa = mantissa + 2;
                }
                if (i == 7) {
                    mantissa = mantissa + 1;
                }
            }
        }
        mantissa = (mantissa / 8.0) + 1;
        result = mantissa * (Math.pow(2, exp));
        float minifloat = (float) result;
        if (bitSequence.charAt(0) == '1') {
            minifloat = -1 * minifloat;
        }
        return minifloat;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            if (Math.round(miniFloatFromString(s)) == miniFloatFromString(s)) {
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
