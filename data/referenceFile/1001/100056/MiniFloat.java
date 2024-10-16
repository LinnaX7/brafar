public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // The sign of the bitSequence number, 0:+ive, 1:-ive
        String sign = Integer.parseInt(String.valueOf(bitSequence.charAt(0))) == 0 ? "+" : "-";
        // The two's complement exponent
        int exponent = 0;
        // if the first digit of the 4 bit exponent is not 1, then it is a positive number
        if (String.valueOf(bitSequence.charAt(1)).equals("0")) {
            // 0 leading 4 bits are considered to be positive
            exponent = Integer.parseInt(bitSequence.substring(1, 5), 2);
        } else // the exponent is a negative number by the rule of 2's complement
        {
            // Retrieving the 4 bit from bitSequence
            String expStr = bitSequence.substring(1, 5);
            // The converted string after applying 1's complement, 0xxx (Left to right)
            String convertedStr = "0";
            // Step 1, Flipping the 4 bit digits (1111 - 1xxx)
            for (int i = 1; i < expStr.length(); i++) {
                // 1 - 0 = 0
                if (expStr.substring(i, i + 1).equals("0")) {
                    convertedStr += "1";
                } else // 1 - 1 = 0
                {
                    convertedStr += "0";
                }
            }
            // Step 2, adding one to the 1's complement string (convertedStr)
            // Temporary place holder for updated string
            String temp = "";
            // unpack[0] = updated carryBit, unpack[1] = updated temp string item
            String[] unpack;
            // Determines if there are any carried bits
            boolean carryBit = false;
            // Starting from right to left order of the convertedStr
            for (int i = convertedStr.length() - 1; i >= 0; i--) {
                if (i == convertedStr.length() - 1) {
                    unpack = doesItCarryBit(convertedStr.substring(i, i + 1), true);
                } else {
                    unpack = doesItCarryBit(convertedStr.substring(i, i + 1), carryBit);
                }
                temp = unpack[0] + temp;
                carryBit = unpack[1].equals("T") ? true : false;
            }
            convertedStr = temp;
            // Step 3, multiply -1 to make the final number negative
            exponent = Integer.parseInt(convertedStr, 2) * -1;
        }
        // unit1   unit2   unit3
        // The mantissa:    0       0       0
        // 0.5    0.25   0.125
        // 2^-1    2^-2    2^-3
        float unit1 = Integer.parseInt(String.valueOf(bitSequence.charAt(5))) == 0 ? 0 : 0.5f;
        float unit2 = Integer.parseInt(String.valueOf(bitSequence.charAt(6))) == 0 ? 0 : 0.25f;
        float unit3 = Integer.parseInt(String.valueOf(bitSequence.charAt(7))) == 0 ? 0 : 0.125f;
        // Assumption provided by assessment 1.mantissa x 2^exponent
        float mantissa = 1.0f + unit1 + unit2 + unit3;
        // The converted result
        float miniFloat = sign.equals("+") ? (float) (mantissa * Math.pow(2, exponent)) : (float) (mantissa * Math.pow(2, exponent) * -1);
        return miniFloat;
    }

    private static String[] doesItCarryBit(String bit, boolean initialCarryBit) {
        String[] unpack = new String[2];
        if (initialCarryBit) {
            unpack[0] = bit.equals("0") ? "1" : "0";
        } else {
            unpack[0] = bit.equals("0") ? "0" : "1";
        }
        unpack[1] = bit.equals("0") ? "F" : "T";
        return unpack;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        // Number of integral minifloat values
        int numOfMiniFloat = 0;
        // Retrieving all valid minifloat sequences
        String[] bitSequence = getValidMiniFloatBitSequences();
        float mfloat = 0.0f;
        // looping though all possible combinations of 8 bit sequences
        for (String s : bitSequence) {
            // Processing the sequence with task 1 method
            mfloat = miniFloatFromString(s);
            // if mfloat (1.125f) - (int)mfloat (1) == 0.0, this means it is an integer
            if (mfloat - (int) mfloat == 0) {
                numOfMiniFloat++;
            }
        }
        return numOfMiniFloat;
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
