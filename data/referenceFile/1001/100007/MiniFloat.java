public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int[] arr = new int[bitSequence.length()];
        float exponent = 0;
        float significand = 0;
        float wholeValue = 0;
        int l = -1;
        String extract = bitSequence.substring(1, 5);
        int calculatedExp;
        if (bitSequence.charAt(1) == '0') {
            calculatedExp = Integer.parseInt(extract, 2);
        } else {
            String swap = extract;
            swap = swap.replace('0', 'x');
            swap = swap.replace('1', '0');
            swap = swap.replace('x', '1');
            calculatedExp = Integer.parseInt(swap, 2);
            calculatedExp = (-1) * (calculatedExp + 1);
        }
        for (int i = 0; i < bitSequence.length(); i++) {
            arr[i] = bitSequence.charAt(i) - '0';
        }
        for (int n = 5; n < 8; n++) {
            significand = (float) (significand + arr[n] * Math.pow(2, l));
            l--;
        }
        float signplusone = 1 + significand;
        if (arr[0] == 0) {
            wholeValue = (float) (Math.pow(2, calculatedExp) * signplusone);
        } else {
            wholeValue = (float) (Math.pow(2, calculatedExp) * signplusone * (-1));
        }
        return wholeValue;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int count = 0;
        String[] a = getValidMiniFloatBitSequences();
        for (int i = 0; i < 256; i++) {
            if (miniFloatFromString(a[i]) % 1 == 0) {
                count++;
            }
        }
        System.out.println(count);
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
        }
        return result;
    }
}
