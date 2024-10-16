public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // used in 2's complement
        int index = 0;
        // store miniFloat value
        float whole_value = 0;
        // variable for exponent
        int exponent = 0;
        // variable for significand
        double significand = 0;
        // convert strings to char array
        char[] s = bitSequence.toCharArray();
        // indicate exponent is positive or not
        boolean exponent_positive = true;
        // calculate exponent , consider 2's complement
        if (s[1] == '0') {
            // positive exponent
            for (int i = 4; i > 1; i--) {
                // convert char to int
                exponent += (int) (Integer.parseInt(String.valueOf(s[i])) * Math.pow(2, (4 - i)));
            }
        }
        if (s[1] == '1') {
            // negative exponent
            exponent_positive = false;
            for (int i = 4; i > 0; i--) {
                if (s[i] == '1') {
                    // record the first appear bit "1"
                    index = i;
                    // then jump out for-loop
                    break;
                }
            }
            // do reverse bit '0' and '1' after index
            for (int i = index - 1; i > 0; i--) {
                if (s[i] == '0') {
                    s[i] = '1';
                    continue;
                }
                if (s[i] == '1') {
                    s[i] = '0';
                    continue;
                }
            }
            for (int i = 4; i > 1; i--) {
                // convert char to int
                exponent += (int) (Integer.parseInt(String.valueOf(s[i])) * Math.pow(2, (4 - i)));
            }
            // special case for '1000' negative exponent
            if (index == 1) {
                exponent = 8;
            }
        }
        // calculate significand
        for (int i = 5; i < 8; i++) {
            // convert char to double
            significand += Integer.parseInt(String.valueOf(s[i])) * Math.pow(2, (4 - i));
        }
        // finish significant part
        significand += 1;
        if (s[0] == '0' && exponent_positive == true) {
            // positive sign and exponent
            whole_value = (float) (significand * Math.pow(2, exponent));
        }
        if (s[0] == '0' && exponent_positive == false) {
            // positive sign but negative exponent
            whole_value = (float) (significand * Math.pow(2, -exponent));
        }
        if (s[0] == '1' && exponent_positive == false) {
            // negative sign and exponent
            // because here exponent is negative
            whole_value = (float) (significand * Math.pow(2, -exponent));
            // make it negative
            whole_value = -whole_value;
        }
        if (s[0] == '1' && exponent_positive == true) {
            // negative sign but positive exponent
            // because here exponent is negative
            whole_value = (float) (significand * Math.pow(2, exponent));
            // make it negative
            whole_value = -whole_value;
        }
        // return miniFloat value finally
        return whole_value;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        // get valid bit sequences
        String[] s = getValidMiniFloatBitSequences();
        // define length - iteration times
        int length = (int) Math.pow(2, MINI_FLOAT_SIZE);
        float mini_Float_value = 0;
        // used to count mini float value that is integer
        int integer_count = 0;
        for (int i = 0; i < length; i++) {
            // get float value of each bit sequence
            mini_Float_value = miniFloatFromString(s[i]);
            if (mini_Float_value % 1 == 0.0 || mini_Float_value % 1 == -0.0) {
                // fractional part for integer is zero
                integer_count++;
            }
        }
        // finally return total number of all integral miniFloat values
        return integer_count;
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
