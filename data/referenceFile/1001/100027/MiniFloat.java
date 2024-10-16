public class MiniFloat {

    public static float DELTA = 1E-6f;

    public static final int MINI_FLOAT_SIZE = 8;

    public static String[] result = getValidMiniFloatBitSequences();

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        String exp, sig;
        float numSig = 0, numExp = 0, num;
        exp = bitSequence.substring(1, 5);
        sig = "1" + bitSequence.substring(5, 8);
        for (int i = 0; i < 4; i++) {
            if (sig.charAt(i) == '1') {
                numSig += Math.pow(0.5, i);
            }
            if (exp.charAt(i) == '1' && i == 0) {
                numExp -= Math.pow(2, 3);
            }
            if (exp.charAt(i) == '1' && i != 0) {
                numExp += Math.pow(2, (3 - i));
            }
        }
        num = (float) (numSig * Math.pow(2, numExp));
        if (bitSequence.charAt(0) == '0') {
            return num;
        }
        return (-1 * num);
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int count = 0;
        for (int i = 0; i < Math.pow(2, MINI_FLOAT_SIZE); i++) {
            if (Math.abs(miniFloatFromString(result[i]) - (float) ((int) (miniFloatFromString(result[i])))) < DELTA)
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
}
