public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        String PN = bitSequence.substring(0, 1);
        String Exponent = bitSequence.substring(1, 5);
        String Mantissa = bitSequence.substring(5, 8);
        int P = Integer.parseInt(PN);
        int sum1 = 0;
        int len1 = Exponent.length();
        if ((Exponent.substring(0, 1)).equals("0")) {
            for (int i = 2; i <= len1; i++) {
                int dt = Integer.parseInt(Exponent.substring(i - 1, i));
                sum1 += (int) Math.pow(2, len1 - i) * dt;
            }
        } else {
            if ((Exponent.substring(0, 1)).equals("1")) {
                for (int i = 2; i <= len1; i++) {
                    int dt = Integer.parseInt(Exponent.substring(i - 1, i));
                    sum1 += (int) Math.pow(2, len1 - i) * dt;
                }
                sum1 -= 8;
            }
        }
        float sum2 = 0;
        int len2 = Mantissa.length();
        for (int i = 1; i <= len2; i++) {
            float dt = Integer.parseInt(Mantissa.substring(i - 1, i));
            sum2 += (float) Math.pow(2, -i) * dt;
        }
        float sum = (sum2 + 1) * (float) Math.pow(2, sum1);
        if (P == 1) {
            sum = 0 - sum;
        }
        return sum;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] all = getValidMiniFloatBitSequences();
        int sum3 = 0;
        int len3 = all.length;
        System.out.println(len3);
        for (int i = 0; i < len3; i++) {
            float x = miniFloatFromString(all[i]);
            int y = (int) x;
            if (y == x) {
                sum3 += 1;
            }
        }
        System.out.println(sum3);
        return sum3;
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
