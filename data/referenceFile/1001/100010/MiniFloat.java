public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // Exponent [1~4]
        int exp = 0;
        if (bitSequence.charAt(1) == '1') {
            for (int i = 2; i < 5; ++i) {
                if (bitSequence.charAt(i) == '0') {
                    exp += 1 << (4 - i);
                }
            }
            exp = -(exp + 1);
        } else {
            exp = Integer.parseInt(bitSequence.substring(1, 5), 2);
        }
        // Mantissa [5~7]
        float man = 1;
        for (int i = 5; i < 8; ++i) {
            if (bitSequence.charAt(i) - '0' == 1) {
                man += Math.pow(2, -(i - 4));
            }
        }
        float rst = man * (float) Math.pow(2, exp);
        // Sign [0]
        return bitSequence.charAt(0) == '1' ? -rst : rst;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] strs = getValidMiniFloatBitSequences();
        int cnt = 0;
        for (int i = 0; i < strs.length; ++i) if (miniFloatFromString(strs[i]) % 1 == 0)
            ++cnt;
        return cnt;
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
