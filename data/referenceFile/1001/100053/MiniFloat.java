public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int[] v = new int[8];
        for (int i = 0; i < 8; i++) {
            v[i] = bitSequence.charAt(i) - '0';
        }
        int sign = 1;
        if (v[0] == 1) {
            sign = -1;
        }
        int exp = 0;
        for (int i = 1; i < 5; i++) {
            exp = exp * 2 + v[i];
        }
        if (v[1] == 1) {
            exp -= 16;
        }
        float mant = 1, p = 0.5f;
        for (int i = 5; i < 8; i++) {
            mant += p * v[i];
            p /= 2;
        }
        if (exp > 0) {
            mant *= 1 << exp;
        } else {
            mant /= 1 << (-exp);
        }
        return sign * mant;
    }

    public static int numIntegralMiniFloats() {
        String[] sequences = getValidMiniFloatBitSequences();
        int cnt = 0;
        for (int i = 0; i < 256; i++) {
            float v = miniFloatFromString(sequences[i]);
            if (v == (int) v)
                cnt++;
        }
        return cnt;
    }

    /**
     * Get all valid bit sequences for miniFloat values.
     */
    private static String[] getValidMiniFloatBitSequences() {
        String[] sequences = new String[256];
        for (int i = 0; i < 256; i++) {
            char[] temp = new char[8];
            int k = i;
            for (int j = 7; j >= 0; j--) {
                temp[j] = (char) (k % 2 + '0');
                k /= 2;
            }
            sequences[i] = new String(temp);
        }
        return sequences;
    }

    private static int MINI_FLOAT_SIZE = 8;
}
