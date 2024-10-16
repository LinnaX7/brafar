public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float flag = 1;
        float exp = 0, temp, ans = 1;
        float sig = 1;
        if (bitSequence.substring(0, 1).equals("1")) {
            flag = -1;
        }
        for (int i = 1; i < 5; i++) {
            temp = Integer.parseInt(bitSequence.substring(i, i + 1));
            if (i == 1 && temp == 1) {
                exp = -8;
            }
            if (i > 1) {
                exp += Math.pow(2, 4 - i) * temp;
            }
        }
        for (int i = 5; i < 8; i++) {
            temp = Integer.parseInt(bitSequence.substring(i, i + 1));
            sig += Math.pow(2, 4 - i) * temp;
        }
        ans *= flag * Math.pow(2, exp) * sig;
        return ans;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        float ans = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            ans = miniFloatFromString(s);
            if (Math.round(ans) == ans) {
                // System.out.printf("%f  %s \n", ans, s);
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
        // System.out.println(result);
        return result;
    }

    private static int MINI_FLOAT_SIZE = 8;
}
