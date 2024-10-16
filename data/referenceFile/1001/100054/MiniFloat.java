public class MiniFloat {

    public static void main(String[] args) {
        printIntegralMiniFloats();
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int exp = (int) (bitSequence.charAt(1) - '0') * (-8) + (int) (bitSequence.charAt(2) - '0') * 4 + (int) (bitSequence.charAt(3) - '0') * 2 + (int) (bitSequence.charAt(4) - '0') * 1;
        float signi = (float) (1.0 + (float) (bitSequence.charAt(5) - '0') * 0.5 + (float) (bitSequence.charAt(6) - '0') * 0.25 + (float) (bitSequence.charAt(7) - '0') * 0.125);
        float ret = signi * (float) Math.pow(2, exp);
        if (bitSequence.charAt(0) == '1') {
            ret = -ret;
        }
        return ret;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            float f_ans = miniFloatFromString(s);
            int i_ans = (int) f_ans;
            if (Math.abs(i_ans - f_ans) < 1e-6)
                ++count;
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

    private static void printIntegralMiniFloats() {
        System.out.println(numIntegralMiniFloats());
    }

    private static int MINI_FLOAT_SIZE = 8;
}
