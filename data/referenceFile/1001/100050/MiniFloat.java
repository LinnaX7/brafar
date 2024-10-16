import static java.lang.Integer.parseInt;

public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int expValue;
        float manValue = 1;
        float miniFloat;
        String binaryValue = bitSequence.substring(1, 5);
        String signExpValue = bitSequence.substring(1, 2);
        String signMiniFloat = bitSequence.substring(0, 1);
        // adjust the sign of exponent
        if (signExpValue.equals("1")) {
            expValue = parseInt(binaryValue, 2) - 16;
        } else {
            expValue = parseInt(binaryValue, 2);
        }
        float m = 2;
        // calculate mantissa
        for (int j = 5; j < 8; j++) {
            // intercept the 5th, 6th, and 7th digits in sequence
            String mantissa = bitSequence.substring(j, j + 1);
            // transfer int from string then give to i
            int i = Integer.valueOf(mantissa).intValue();
            // calculate the value of mantissa
            manValue = manValue + i * (1 / m);
            m = m * 2;
        }
        miniFloat = (float) (manValue * Math.pow(2, expValue));
        if (signMiniFloat.equals("1")) {
            miniFloat = 0 - (miniFloat);
        }
        return miniFloat;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            // int miniValue =(int)(miniFloatFromString (s));
            if (miniFloatFromString(s) - (int) (miniFloatFromString(s)) == 0f) {
                count++;
                System.out.println(miniFloatFromString(s));
            }
        }
        System.out.println("The number of count is: " + count);
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
