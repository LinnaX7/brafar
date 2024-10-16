public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float res = 0;
        String exp, man;
        char sign;
        sign = bitSequence.charAt(0);
        exp = bitSequence.substring(1, 5);
        man = bitSequence.substring(5, 8);
        int exp1 = Integer.parseInt(exp, 2);
        if (exp1 >= 8) {
            exp1 -= 16;
        }
        double sig = Integer.parseInt(man, 2) / 8.0 + 1.0;
        res = (float) (sig * Math.pow(2, exp1));
        if (sign == '1') {
            res = -res;
        }
        return res;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            if (Math.abs(miniFloatFromString(s) % 1.0) <= 1e-6)
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

    private static int MINI_FLOAT_SIZE = 8;
}
