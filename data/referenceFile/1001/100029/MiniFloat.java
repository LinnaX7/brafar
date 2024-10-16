import static java.lang.Math.pow;

public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // get sign value
        int sign = Integer.parseInt(bitSequence.substring(0, 1));
        // get Exponent
        String binExp = bitSequence.substring(1, 5);
        int decExp;
        if (bitSequence.charAt(1) == '0') {
            decExp = Integer.parseInt(binExp, 2);
        } else {
            String invert = binExp;
            invert = invert.replace("0", " ");
            invert = invert.replace("1", "0");
            invert = invert.replace(" ", "1");
            decExp = Integer.parseInt(invert, 2);
            decExp = -(decExp + 1);
        }
        // get mantissa
        String binManti = bitSequence.substring(5, 8);
        float manti = 0;
        float temp = 2;
        for (int i = 0; i < binManti.length(); i++) {
            manti += (binManti.charAt(i) - '0') / temp;
            temp *= 2;
        }
        manti = 1 + manti;
        // get miniFloat value
        float miniFloat = (float) (manti * pow(2, decExp));
        miniFloat = (sign == 1 ? -miniFloat : miniFloat);
        return miniFloat;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] allString = getValidMiniFloatBitSequences();
        float temp;
        int num = 0;
        for (String s : allString) {
            temp = miniFloatFromString(s);
            if (temp == Math.floor(temp)) {
                num++;
            }
        }
        System.out.print(num);
        return num;
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
