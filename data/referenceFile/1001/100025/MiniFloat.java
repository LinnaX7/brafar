public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float miniFloatValue = 1;
        String mantissa = bitSequence.substring(5, 8);
        for (int i = 0; i < mantissa.length(); i++) {
            if (mantissa.charAt(i) == '1') {
                miniFloatValue += Math.pow(0.5, i + 1);
            }
        }
        if (bitSequence.charAt(0) == '1') {
            miniFloatValue *= -1;
        }
        String expoString = bitSequence.substring(1, 5);
        int expo = 0;
        for (int i = 1; i < expoString.length(); i++) {
            if (expoString.charAt(i) == '1') {
                expo += Math.pow(2, expoString.length() - i - 1);
            }
        }
        if (expoString.charAt(0) == '1') {
            expo -= Math.pow(2, expoString.length() - 1);
        }
        miniFloatValue *= Math.pow(2, expo);
        return miniFloatValue;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            float miniFloatValue = miniFloatFromString(s);
            // System.out.println(s+"    "+miniFloatValue);
            if (!(Math.abs(miniFloatValue - (int) miniFloatValue) > 0)) {
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
        return result;
    }

    private static int MINI_FLOAT_SIZE = 8;
}
