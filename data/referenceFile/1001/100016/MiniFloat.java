public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Receive a string and return the value of this string as miniFloat in decimal format
        // Get the sign and the mantissa
        char[] charList = bitSequence.toCharArray();
        char sig = charList[0];
        String manStr = new String(charList, 5, 3);
        int man = Integer.parseInt(manStr, 2);
        // Get the exponent
        String expStr = new String(charList, 2, 3);
        int exp = Integer.parseInt(expStr, 2);
        if (charList[1] == '1') {
            exp = -1 * (8 - exp);
        }
        // Calculate the value
        float value = (float) Math.pow(2, (double) exp) * (1 + (float) man / 8);
        if (sig == '1') {
            value *= -1;
        }
        return value;
    }

    public static int numIntegralMiniFloats() {
        // Return the number of integral miniFloat values
        int counter = 0;
        String[] allMiniFloat = getValidMiniFloatBitSequences();
        for (String str : allMiniFloat) {
            float num = miniFloatFromString(str);
            if (num % 1 == 0) {
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
