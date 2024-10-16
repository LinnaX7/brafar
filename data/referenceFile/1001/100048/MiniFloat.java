public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    private static boolean isInteger(float minifloat) {
        // new func; implemented to judge whether the argument is an integer
        Float minifloatObj = new Float(minifloat);
        return minifloatObj.intValue() == minifloat;
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int mant, expo;
        // first digit represent positive or negative
        float result = (bitSequence.charAt(0) == '0') ? 1 : -1;
        // translating bin to dec (both mantissa and exponent)
        expo = (bitSequence.charAt(1) == '0') ? 0 : -8;
        expo += Integer.parseInt(bitSequence.substring(2, 5), 2);
        mant = Integer.parseInt(bitSequence.substring(5), 2);
        result *= (float) (1.0 + mant / 8.0) * (Math.pow(2.0, expo));
        return result;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] wholeSeq = getValidMiniFloatBitSequences();
        int count = 0;
        for (int i = 0; i < wholeSeq.length; i++) {
            if (isInteger(miniFloatFromString(wholeSeq[i]))) {
                // System.out.println(miniFloatFromString(result[i]));
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
}
