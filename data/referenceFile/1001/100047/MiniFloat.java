public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float numt = 0;
        boolean ispositive = true;
        if (bitSequence.charAt(0) - '0' == 1) {
            ispositive = false;
        }
        int pown = 0;
        int[] a = new int[3];
        for (int i = 0; i < 3; i++) {
            a[i] = bitSequence.charAt(i + 2) - '0';
        }
        if (bitSequence.charAt(1) - '0' == 0) {
            pown = 4 * a[0] + 2 * a[1] + a[2];
        } else {
            for (int i = 0; i < 3; i++) {
                if (a[i] == 0) {
                    a[i] = 1;
                } else {
                    a[i] = 0;
                }
            }
            pown = 4 * a[0] + 2 * a[1] + a[2] + 1;
            pown *= -1;
        }
        for (int i = 0; i < 3; i++) {
            a[i] = bitSequence.charAt(i + 5) - '0';
        }
        numt = (float) (1.0 + a[0] * 1.0 / 2 + a[1] * 1.0 / 4 + a[2] * 1.0 / 8);
        if (ispositive == false) {
            numt *= -1.0;
        }
        numt *= Math.pow(2.0, pown);
        return numt;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            if (miniFloatFromString(s) % 1 == 0)
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
