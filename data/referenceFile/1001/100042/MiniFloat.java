import java.util.Arrays;

public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // s is the sign bit of the miniFloat, 1 is negative.
        float s = (float) Math.pow(-1, bitSequence.toCharArray()[0] - '0');
        // exp is the 4-bits 2's complement, E is the exact decimal value of exp
        char[] exp = bitSequence.substring(1, 5).toCharArray();
        // 2's complement's sign bit * -8
        int E = -8 * (exp[0] - '0');
        int j = 4;
        for (char bit : Arrays.copyOfRange(exp, 1, 4)) {
            E += j * (bit - '0');
            j /= 2;
        }
        // frac is the mantissa, M is the exact decimal value of frac
        char[] frac = bitSequence.substring(5).toCharArray();
        float M = 1.0F;
        float i = 0.5F;
        for (char bit : frac) {
            M += i * (bit - '0');
            i /= 2;
        }
        return s * (float) Math.pow(2, E) * M;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int count = 0;
        for (String seq : getValidMiniFloatBitSequences()) {
            // change to decimal value
            float num = miniFloatFromString(seq);
            if (num == (int) num) {
                // if num is integer, float == int
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
