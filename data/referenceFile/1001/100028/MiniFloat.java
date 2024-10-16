public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    private static int getExp(String bitSequence) {
        int exp = 0;
        for (int i = 1; i < 5; ++i) {
            exp = exp << 1;
            if (bitSequence.charAt(i) == '1')
                ++exp;
        }
        if ((exp & 8) != 0)
            exp = -(16 - exp);
        return exp;
    }

    private static int getLen(String bitSequence) {
        for (int i = 7; i > 4; --i) {
            if (bitSequence.charAt(i) == '1')
                return 4 - i;
        }
        return 0;
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int exp = getExp(bitSequence);
        int temp = 1;
        for (int i = 5; i < 8; ++i) {
            temp = temp << 1;
            if (bitSequence.charAt(i) == '1') {
                ++temp;
            }
        }
        float answer = ((float) temp / 8.0f) * (float) Math.pow(2, exp);
        if (bitSequence.charAt(0) == '1') {
            answer = -answer;
        }
        return answer;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            if ((getExp(s) + getLen(s)) >= 0) {
                ++count;
                // System.out.print(miniFloatFromString(s));System.out.print('\n');
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
