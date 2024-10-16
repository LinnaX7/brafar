public class MiniFloat {

    static void printIntegralMiniFloats() {
        int totalnums = numIntegralMiniFloats();
        System.out.println(totalnums);
    }

    public static void main(String[] args) {
        printIntegralMiniFloats();
    }

    static double pow(int i, int expoent) {
        double instance = Double.valueOf(i);
        if (expoent > 0) {
            if (expoent % 2 == 0) {
                instance = instance * instance;
                double j = instance;
                for (int t = 1; t < expoent / 2; t++) instance *= j;
            } else {
                double j = i;
                for (int k = 1; k < expoent; k++) instance *= j;
            }
        } else if (expoent < 0) {
            instance = 1;
            for (int y = 0; y < Math.abs(expoent); y++) instance = instance / 2;
        } else if (expoent == 0) {
            return 1;
        }
        return instance;
    }

    public static double miniFloatFromString(String a) {
        String[] temptransfer = a.trim().split("");
        double larger0 = 0, precious = 0;
        double instance;
        for (int i = 1; i <= 4; i++) {
            if (temptransfer[i].equals("1")) {
                larger0 += pow(2, 4 - i);
                if (4 - i > 2) {
                    larger0 *= -1;
                }
            }
        }
        for (int k = 5; k < a.length(); k++) {
            if (temptransfer[k].equals("1")) {
                precious += pow(2, 4 - k);
            }
        }
        precious += 1;
        instance = precious * pow(2, (int) larger0);
        if (a.substring(0, 1).equals("1")) {
            instance = instance * (-1);
        }
        return instance;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        double minmun = 6E-20;
        for (String s : getValidMiniFloatBitSequences()) {
            double instance = miniFloatFromString(s);
            if (instance - (int) instance < minmun)
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
