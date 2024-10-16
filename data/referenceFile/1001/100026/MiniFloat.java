public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        double ans = 1;
        int exp = 0;
        for (int i = 0; i <= 3; i++) {
            exp = bitSequence.charAt(4 - i) == '1' ? exp + (int) Math.pow(2, i) : exp;
        }
        if (bitSequence.charAt(1) == '1') {
            exp -= 16;
        }
        for (int i = -1; i >= -3; i--) {
            ans = bitSequence.charAt(4 - i) == '1' ? ans + Math.pow(2, i) : ans;
        }
        ans *= Math.pow(2, exp);
        if (bitSequence.charAt(0) == '1') {
            ans = -ans;
        }
        return (float) ans;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            float num = miniFloatFromString(s);
            if (num % 1 == 0)
                count++;
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
