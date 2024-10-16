public class MiniFloat {

    public static void main(String[] args) {
        // print result
        miniFloatFromString("0");
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // value as the final output number of the 8-bit binary
        float value = 0;
        // take the 2-5 as exponent number
        String pick1 = bitSequence.substring(1, 5);
        // take the 6-8 as mantissa
        String pick2 = bitSequence.substring(5, 8);
        // take the second number to detect whether the exponent number is positive or negative
        String sign = bitSequence.substring(1, 2);
        // take the first number as the sign number and change from string to int for comparison
        int pickFirst = Integer.valueOf(bitSequence.substring(0, 1)).intValue();
        // first change the exponent from binary to decimal for the value that second number is 0
        int exponent = Integer.valueOf((pick1), 2);
        if (sign.equals("1")) {
            // in second number is "1", get the reverse exponent for the negative exponent
            exponent -= 16;
        }
        double dec = binToDec(pick2);
        if (pickFirst == 1) {
            // if the first number is "1", then the value is negative, or the value should be positive finally
            value = (float) ((-1) * (1 + dec) * Math.pow(2, exponent));
        } else {
            value = (float) (((1 + dec) * Math.pow(2, exponent)));
        }
        return value;
    }

    public static float binToDec(String pick2) {
        // get the last three number as the value's mantissa
        float dec = 0.0f;
        for (int i = 0; i < pick2.length(); i++) {
            int mantissa = pick2.charAt(i) - '0';
            // for different position of the number
            mantissa = -(i + 1) * mantissa;
            if (mantissa != 0) {
                // only not equal to zero the value could be added
                // add different position value together as the reverse of the transformation from decimal to binary
                dec += Math.pow(2, mantissa);
            }
        }
        return dec;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            // get each member in string s
            // and store it into tranS
            float tranS = miniFloatFromString(s);
            if (Math.round(tranS) == tranS) {
                // detect after round(), whether two number is same, if so, it is integer
                // and count +1 if it's integer
                count++;
            }
        }
        System.out.println(count);
        return count;
    }

    private static String[] getValidMiniFloatBitSequences() {
        // 获取2^8个8位的bit
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return result;
    }

    private static int MINI_FLOAT_SIZE = 8;
}
// OOP Assignment 1, Question 1
// CHEN Derun, Shanpoo
// 21098424d
