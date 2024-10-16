public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        float result = 0;
        int[] a = new int[MINI_FLOAT_SIZE];
        for (int i = 0; i < MINI_FLOAT_SIZE; i++) {
            a[i] = Integer.parseInt(String.valueOf(bitSequence.charAt(i)));
        }
        double times = 0;
        if (a[1] == 1) {
            for (int i = 2; i <= 4; i++) {
                if (a[i] == 0) {
                    a[i] = 1;
                } else {
                    a[i] = 0;
                }
            }
            times = 0 - (a[2] * 4 + a[3] * 2 + a[4] + 1);
        } else {
            times = a[2] * 4 + a[3] * 2 + a[4];
        }
        double number = Math.pow(2.0, times);
        double sub = a[5] * 0.5 + a[6] * 0.25 + a[7] * 0.125 + 1;
        double resultOriginal = number * sub;
        if (a[0] == 1) {
            resultOriginal = 0 - resultOriginal;
        }
        result = (float) resultOriginal;
        return result;
    }

    public static void printIntegralMiniFloats() {
        int totalnumber = 0;
        for (String s : getValidMiniFloatBitSequences()) {
            float resultNumber = miniFloatFromString(s);
            String numberString = String.valueOf(resultNumber);
            String newStringnumber = subZeroAndDot(numberString);
            if (numberOfInterger(newStringnumber)) {
                totalnumber++;
            }
            System.out.println(s + " == " + newStringnumber);
        }
        System.out.println("Total number of integral miniFloat values: <" + totalnumber + ">");
    }

    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    public static Boolean numberOfInterger(String s) {
        if (s.indexOf(".") == -1) {
            return true;
        } else {
            return false;
        }
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
