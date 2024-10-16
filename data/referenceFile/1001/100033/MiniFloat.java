public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        // Get different part
        String sign = bitSequence.substring(0, 1), e = bitSequence.substring(1, 5), m = bitSequence.substring(5);
        // Cal the exponent
        int e_val = twoComplement(e);
        // Cal the mantissa
        float m_val = 1;
        int m_len = m.length();
        for (int i = 0; i < m_len; i++) {
            if (m.charAt(i) == '1') {
                m_val += (1.0 / (1 << (i + 1)));
            }
        }
        // Cal the value
        float val = (float) (m_val * ((e_val >= 0) ? (1 << e_val) : (1.0 / (1 << (e_val * -1)))));
        // Check return a positive or negative value
        return (sign.charAt(0) == '1' ? val * -1 : val);
    }

    private static int twoComplement(String bitSequence) {
        if (bitSequence.charAt(0) == '0') {
            // Positive
            int val = 0, len = bitSequence.length();
            for (int i = len - 1; i >= 0; i--) {
                if (bitSequence.charAt(i) == '1') {
                    val += 1 << (len - 1 - i);
                }
            }
            return val;
        } else {
            // negative
            String newbit = "";
            char[] chars = bitSequence.toCharArray();
            for (char ch : chars) {
                newbit += (ch == '1') ? '0' : '1';
            }
            int val = 0, len = newbit.length();
            for (int i = len - 1; i >= 0; i--) {
                if (newbit.charAt(i) == '1') {
                    val += 1 << (len - 1 - i);
                }
            }
            return (val + 1) * -1;
        }
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            // Get the value
            float val = miniFloatFromString(s);
            if (val == (int) val) {
                count++;
            }
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
