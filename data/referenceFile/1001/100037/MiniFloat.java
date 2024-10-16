public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        int signBit = Integer.parseInt(bitSequence.substring(0, 1));
        // int expBit = Integer.parseInt(bitSequence.substring(1, 5));
        // int sigBit = Integer.parseInt(bitSequence.substring(5));
        // String signBit = bitSequence.substring(0, 1);
        String expBit = bitSequence.substring(1, 5);
        String sigBit = bitSequence.substring(5);
        float manti = 0;
        for (int i = 3; i > 0; i--) {
            manti = (manti + Integer.parseInt(sigBit.substring(i - 1, i))) / 2;
        }
        manti = manti + 1;
        double expo = 0;
        for (int i = 4; i > 1; i--) {
            expo = expo + (Integer.parseInt(expBit.substring(i - 1, i)) * Math.pow(2, (4 - i)));
            // System.out.println("expo:" + Integer.parseInt(expBit.substring(i-1, i)) + expo);
        }
        // System.out.println("Exponent Sign: " + Integer.parseInt(expBit.substring(0, 1)));
        if (Integer.parseInt(expBit.substring(0, 1)) == 1) {
            expo = expo - 8;
        }
        int sign;
        if (signBit == 0) {
            sign = 1;
        } else {
            sign = -1;
        }
        double absVal = sign * manti * Math.pow(2, expo);
        // System.out.println("OG:" + bitSequence);
        // System.out.println("signbit:" + signBit);
        // System.out.println("expbit:" + expBit);
        // System.out.println("expo:" + expo);
        // System.out.println("sigbit:" + sigBit);
        // System.out.println("Mantissa:" + manti);
        // System.out.println("AbsValue:" + absVal);
        float retVal = (float) absVal;
        return retVal;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        String[] nBitFloatsArr = getValidMiniFloatBitSequences();
        // System.out.println("Results:" + Arrays.toString(nBitFloatsArr));
        int integerNo = 0;
        for (int i = 0; i < nBitFloatsArr.length; i++) {
            // System.out.println(i + ": " + miniFloatFromString(nBitFloatsArr[i]));
            if (Math.abs(miniFloatFromString(nBitFloatsArr[i])) % 1 < 1E-6f) {
                // System.out.println(miniFloatFromString(nBitFloatsArr[i]) % 1);
                integerNo++;
            }
        }
        // System.out.println("No.:" + integerNo);
        return integerNo;
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
