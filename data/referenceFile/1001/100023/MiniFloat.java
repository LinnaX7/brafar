public class MiniFloat {

    public static float DELTA = 1E-6f;

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int length = bitSequence.length();
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            char c = bitSequence.charAt(i);
            result[i] = Character.getNumericValue(c);
        }
        int exponent = result[1] * (-8) + result[2] * 4 + result[3] * 2 + result[4];
        float mantissa = 1 + (result[5] * 0.5f + result[6] * 0.25f + result[7] * 0.125f);
        float number = (float) (Math.pow(2, exponent) * mantissa);
        int sign = result[0];
        if (sign == 0) {
            return number;
        }
        return -number;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            float number = miniFloatFromString(s);
            if (Math.abs(number - Math.round(number)) < DELTA) {
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
