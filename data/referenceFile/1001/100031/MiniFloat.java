public class MiniFloat {

    public static void main(String[] args) {
        printIntegralMiniFloats();
    }

    public static void printIntegralMiniFloats() {
        for (String s : getValidMiniFloatBitSequences()) {
            if (miniFloatFromString(s) % 1 == 0) {
                System.out.println("The miniFloat is " + s + " and it is equal to " + miniFloatFromString(s));
            }
        }
    }

    public static float miniFloatFromString(String bitSequence) {
        int sign = Character.getNumericValue(bitSequence.charAt(0));
        if (sign == 0) {
            sign = 1;
        } else {
            sign = -1;
        }
        int j = 2;
        double manti = (-1 * Character.getNumericValue(bitSequence.charAt(1)) * Math.pow(2, 3));
        for (int i = 2; i < 5; i++) {
            manti += (Character.getNumericValue(bitSequence.charAt(i)) * Math.pow(2, j));
            j--;
        }
        double sin = 1;
        for (int i = 5; i < bitSequence.length(); i++) {
            sin += Character.getNumericValue(bitSequence.charAt(i)) * Math.pow(2, j);
            j--;
        }
        return (float) (sign * sin * Math.pow(2, manti));
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            if (miniFloatFromString(s) % 1 == 0) {
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
