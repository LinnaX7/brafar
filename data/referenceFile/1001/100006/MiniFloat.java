public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float sign, in_sign = 1, mantissa = 1, result, exp = 0;
        int i;
        if (bitSequence.charAt(0) == '1') {
            sign = -1;
        } else {
            sign = 1;
        }
        if (bitSequence.charAt(1) == '1') {
            in_sign = -1;
            if (bitSequence.charAt(4) == '1') {
                bitSequence = bitSequence.substring(0, 4) + '0' + bitSequence.substring(5);
            } else {
                if (bitSequence.charAt(3) == '1') {
                    bitSequence = bitSequence.substring(0, 4) + '1' + bitSequence.substring(5);
                    ;
                    bitSequence = bitSequence.substring(0, 3) + '0' + bitSequence.substring(4);
                } else {
                    bitSequence = bitSequence.substring(0, 3) + '1' + bitSequence.substring(4);
                    bitSequence = bitSequence.substring(0, 2) + '0' + bitSequence.substring(3);
                }
            }
            for (i = 2; i < 5; i++) {
                if (bitSequence.charAt(i) == '1') {
                    bitSequence = bitSequence.substring(0, i) + '0' + bitSequence.substring(i + 1);
                } else {
                    bitSequence = bitSequence.substring(0, i) + '1' + bitSequence.substring(i + 1);
                }
            }
        }
        for (i = 2; i < 5; i++) {
            if (bitSequence.charAt(i) == '1') {
                exp += Math.pow(2, 4 - i);
            }
        }
        exp *= in_sign;
        for (i = 5; i < 8; i++) {
            if (bitSequence.charAt(i) == '1') {
                mantissa += 1 / Math.pow(2, i - 4);
            }
        }
        result = mantissa;
        result *= Math.pow(2, exp);
        result *= sign;
        return result;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] allsequences = getValidMiniFloatBitSequences();
        int count = 0, i;
        for (i = 0; i < allsequences.length; i++) {
            if (Float.compare(miniFloatFromString(allsequences[i]) % 1, 0) == 0)
                count++;
        }
        System.out.print(count);
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
        System.out.print(result);
        return result;
    }
}
