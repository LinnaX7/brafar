public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float result;
        float exponent = 0;
        int power = 2;
        if (bitSequence.charAt(1) == '0') {
            for (int i = 2; i < 5; i++) {
                if (bitSequence.charAt(i) == '1') {
                    exponent += Math.pow(2, power);
                }
                power--;
            }
        } else {
            if (bitSequence.charAt(1) == '1') {
                for (int i = 2; i < 5; i++) {
                    if (bitSequence.charAt(i) == '0') {
                        exponent += Math.pow(2, power);
                    }
                    power--;
                }
                exponent += 1;
                exponent *= -1;
            }
        }
        int power2 = -1;
        float significand = 1;
        for (int i = 5; i < 8; i++) {
            if (bitSequence.charAt(i) == '1') {
                significand += Math.pow(2, power2);
            }
            power2--;
        }
        result = significand * (float) Math.pow(2, exponent);
        if (bitSequence.charAt(0) == '1') {
            result *= -1;
        }
        return result;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int result = 0;
        String[] array1 = getValidMiniFloatBitSequences();
        for (int i = 0; i < Math.pow(2, MINI_FLOAT_SIZE); i++) {
            if (((miniFloatFromString(array1[i]) % 1) == 0)) {
                result += 1;
            }
        }
        return result;
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
