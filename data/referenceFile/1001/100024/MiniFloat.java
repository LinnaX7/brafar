public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        String e = bitSequence.substring(0, 1);
        double np = Double.parseDouble(e);
        double exp = 0;
        double sig = 1;
        String d = bitSequence.substring(1, 2);
        double j = Double.parseDouble(d);
        for (int n = 2; n < 5; n++) {
            String f = bitSequence.substring(n, n + 1);
            double i = Double.parseDouble(f);
            exp = exp + i * Math.pow(2, (4 - n));
        }
        if (j == 1.0) {
            exp = exp - 8;
        }
        for (int n = 5; n < 8; n++) {
            String f = bitSequence.substring(n, n + 1);
            double i = Double.parseDouble(f);
            sig = sig + i * Math.pow(2, (4 - n));
        }
        double answer;
        if (np == 0.0) {
            answer = sig * Math.pow(2, exp);
        } else {
            answer = 0.0 - sig * Math.pow(2, exp);
        }
        float miniFloat = (float) answer;
        return miniFloat;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            System.out.println(miniFloatFromString(s));
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
