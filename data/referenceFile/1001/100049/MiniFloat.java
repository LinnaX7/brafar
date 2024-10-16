// Ho Man Hin (20059357D)
public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float s = 1;
        int e = 0;
        float m = (float) 1.0;
        float result;
        // Sign
        if (bitSequence.charAt(0) == '1') {
            s = -1;
        }
        // Exponent (two's complement)
        if (bitSequence.charAt(1) == '1') {
            e -= 8;
        }
        for (int i = 2; i < 5; i++) {
            if (bitSequence.charAt(i) == '1') {
                e += Math.pow(2, 4 - i);
            }
        }
        // Mantissa
        for (int i = 5; i < 8; i++) {
            if (bitSequence.charAt(i) == '1') {
                m += (float) 1.0 / Math.pow(2, i - 4);
            }
        }
        result = (float) s * ((float) Math.pow(2, e)) * m;
        return result;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        float result;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            result = miniFloatFromString(s);
            if (result == (float) ((int) result)) {
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
