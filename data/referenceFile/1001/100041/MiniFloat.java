public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // Sign:  '0' - '0' = 0 (Positive) ; '1' - '0' = 1 (Negative)
        int miniSign = bitSequence.charAt(0) - '0';
        // Exponent = the substring of bitSequence form index 1 to 5
        // If the content located in index 1 is 1, then it is a positive number
        int miniExponent = 0;
        if (bitSequence.charAt(1) == '0') {
            miniExponent = Integer.parseInt(bitSequence.substring(2, 5), 2);
        } else // Otherwise it is negative number and needs to undergo conversion
        // Subtracting the number by 1
        {
            // Converting string to char array
            char[] bitArray = new char[4];
            for (int i = 0; i < 4; i++) {
                bitArray[i] = bitSequence.charAt(1 + i);
            }
            // If the content of index 4 is 1, then subtract 1 directly
            if (bitArray[3] == '1') {
                bitArray[3] = '0';
            } else // Otherwise, if index 3 is 1, undergo subtraction accordingly
            {
                if (bitArray[2] == '1') {
                    bitArray[3] = '1';
                    bitArray[2] = '0';
                } else // Otherwise, if index 2 is 1, undergo subtraction accordingly
                {
                    if (bitArray[1] == '1') {
                        bitArray[3] = '1';
                        bitArray[2] = '1';
                        bitArray[1] = '0';
                    } else // Otherwise, if index 1 is 1, undergo subtraction accordingly
                    {
                        bitArray[3] = '1';
                        bitArray[1] = '1';
                        bitArray[2] = '1';
                        bitArray[0] = '0';
                    }
                }
            }
            // Flipping the bits
            int temp = 1 - (bitArray[0] - '0');
            bitArray[0] = (char) (temp + '0');
            temp = 1 - (bitArray[1] - '0');
            bitArray[1] = (char) (temp + '0');
            temp = 1 - (bitArray[2] - '0');
            bitArray[2] = (char) (temp + '0');
            temp = 1 - (bitArray[3] - '0');
            bitArray[3] = (char) (temp + '0');
            String tempString = new String(bitArray);
            // Final Exponent Value
            miniExponent = -1 * (Integer.parseInt(tempString, 2));
        }
        // Mantissa = the char of bitSequence multiplied by the corresponding power of 2
        float miniMantissa = 1 + (bitSequence.charAt(5) - '0') * (0.5f) + (bitSequence.charAt(6) - '0') * (0.25f) + (bitSequence.charAt(7) - '0') * (0.125f);
        // Return Value = (+ / -) * (Mantissa) * (2^miniExponent)
        float retVal = (miniSign == 0 ? 1 : -1) * (miniMantissa) * (float) (Math.pow(2, miniExponent));
        return retVal;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        // Generate valid miniFloat bit sequences with the method provided
        String[] someSequences = getValidMiniFloatBitSequences();
        int retCount = 0;
        // Check each of the sequence, whether the number is integral or not
        for (int i = 0; i < someSequences.length; i++) {
            float currentSequenceVal = miniFloatFromString(someSequences[i]);
            if (currentSequenceVal == (int) currentSequenceVal) {
                retCount++;
            }
        }
        return retCount;
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
