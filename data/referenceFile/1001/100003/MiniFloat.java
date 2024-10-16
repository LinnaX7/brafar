public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int s = 1, ev = 0;
        float val, mant = 1, e;
        if (bitSequence.charAt(0) == '1') {
            s = -1;
        }
        for (int i = 4; i > 1; i--) {
            if (bitSequence.charAt(i) == '1') {
                ev += Math.pow(2, 4 - i);
            }
        }
        if (bitSequence.charAt(1) == '1') {
            ev = ev - 8;
        }
        for (int i = 5; i < 8; i++) {
            if (bitSequence.charAt(i) == '1') {
                mant += Math.pow(2, 4 - i);
            }
        }
        e = (float) Math.pow(2, ev);
        val = s * e * mant;
        return val;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int l, count = 0, e = 0, m = 0;
        String[] result = new String[8];
        result = getValidMiniFloatBitSequences();
        l = result.length;
        for (int h = 0; h < l; h++) {
            e = 0;
            m = 0;
            if (result[h].charAt(1) == '1') {
                continue;
            }
            for (int i = 4; i > 1; i--) {
                if (result[h].charAt(i) == '1') {
                    e += Math.pow(2, 4 - i);
                }
            }
            for (int i = 5; i < 8; i++) {
                if (result[h].charAt(i) == '1') {
                    m += Math.pow(2, i - 4);
                }
            }
            if (e >= m) {
                count += 1;
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
