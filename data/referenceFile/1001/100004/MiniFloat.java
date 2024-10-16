public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static int count;

    public static float miniFloatFromString(String bitSequence) {
        int exP = 0;
        float maN = 1;
        // separate the calculation into three components
        float miniValue;
        if (bitSequence.charAt(1) == '1') {
            exP -= 8;
        }
        for (int i = 2; i < 5; i++) {
            if (bitSequence.charAt(i) == '1') {
                // get the value of exponent
                exP += Math.pow(2, 4 - i);
            }
        }
        for (int i = 5; i < MINI_FLOAT_SIZE; i++) {
            if (bitSequence.charAt(i) == '1') {
                // get the value fo mantissa
                maN += Math.pow(2, 4 - i);
            }
        }
        // get the value of nimifloat
        miniValue = (float) ((Math.pow(2, exP)) * maN);
        if (bitSequence.charAt(0) == '1') {
            return (-1) * miniValue;
        }
        // get the right sign
        return miniValue;
        // Task 1: compute the miniFloat value from "bitSequence";
    }

    public static int numIntegralMiniFloats() {
        getValidMiniFloatBitSequences();
        int a = (int) Math.pow(2, (count / 16));
        int b = (int) Math.pow(2, 4);
        if (true)
            return a * b;
        // Task 2: return the number of integral miniFloat values
        return 0;
    }

    /**
     * Get all valid bit sequences for miniFloat values.
     */
    private static String[] getValidMiniFloatBitSequences() {
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
            count++;
        }
        return result;
    }
}
