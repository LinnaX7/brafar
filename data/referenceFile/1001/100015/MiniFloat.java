public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // initialize
        char[] charBitSeq = bitSequence.toCharArray();
        float result = 0, exponents = 0, mantissa = 1;
        // calculate the value of exponents
        for (int i = 2; i <= 4; i++) {
            if (charBitSeq[i] == '1') {
                exponents += Math.pow(2, (4 - i));
            }
        }
        // takes 2's complement
        if (charBitSeq[1] == '1') {
            exponents = exponents - 8;
        }
        // calculate the value of mantissa
        for (int i = 5; i <= 7; i++) {
            if (charBitSeq[i] == '1') {
                mantissa += Math.pow(2, (4 - i));
            }
        }
        // calculate the value
        result = mantissa * (float) Math.pow(2, exponents);
        // sign bit == 1 -> negative value
        if (charBitSeq[0] == '1') {
            result *= -1.0;
        }
        return result;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        // counter
        int totalNum = 0;
        // get all bits
        String[] allValidBits = getValidMiniFloatBitSequences();
        // get the length of the array of all the valid bits
        int totalNumOfAllBits = allValidBits.length;
        float currFloat;
        for (int i = 0; i < totalNumOfAllBits; i++) {
            currFloat = miniFloatFromString(allValidBits[i]);
            // check whether it is an integral value
            if (currFloat % 1.0 == 0) {
                // test
                // System.out.println(allValidBits[i]+"\t"+currFloat);
                totalNum += 1;
            }
        }
        return totalNum;
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
    /*
    //test
    public static void main(String[] args){

        int i = numIntegralMiniFloats();

        System.out.println(i);

        //System.out.println(miniFloatFromString("00100110"));
        //System.out.println(miniFloatFromString("11000000"));
    }
    */
}
