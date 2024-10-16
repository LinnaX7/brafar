public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static double miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int exponent = 0;
        double mantissa = 1.0, value;
        for (int i = 2; i < 5; i++) {
            exponent += ((int) (bitSequence.charAt(i)) - (int) ('0')) * Math.pow(2, (4 - i));
        }
        exponent -= ((int) (bitSequence.charAt(1)) - (int) ('0')) * Math.pow(2, 3);
        for (int i = 5; i < 8; i++) {
            mantissa += ((int) (bitSequence.charAt(i)) - (int) ('0')) * Math.pow(2, (4 - i));
        }
        value = mantissa * Math.pow(2, exponent);
        if (((int) (bitSequence.charAt(0)) - (int) ('0')) == 1) {
            value = 0 - value;
        }
        return value;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] sequencesList = getValidMiniFloatBitSequences();
        int counter = 0;
        for (String bitSequence : sequencesList) {
            double value = miniFloatFromString(bitSequence);
            if ((Math.abs(value - (int) value) < 1E-6f)) {
                counter++;
            }
        }
        return counter;
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
