public class MiniFloat {

    public static void main(String[] args) {
        // revised version
        System.out.println(numIntegralMiniFloats());
        // printIntegralMiniFloats();
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        char sign = bitSequence.charAt(0);
        String expo = bitSequence.substring(1, 5);
        String manti = bitSequence.substring(5, 8);
        int expoInt = 0;
        float fraction = 0.0f;
        char temp;
        for (int i = 1; i < 4; i++) {
            temp = expo.charAt(i);
            if (temp == '1') {
                expoInt += Math.pow(2, 3 - i);
            }
        }
        // using two's complement
        temp = expo.charAt(0);
        if (temp == '1') {
            expoInt -= 8;
        }
        for (int i = 0; i < 3; i++) {
            temp = manti.charAt(i);
            if (temp == '1') {
                fraction += Math.pow(0.5, i + 1);
            }
        }
        fraction += 1.0f;
        fraction *= Math.pow(2, expoInt);
        if (sign == '1') {
            fraction *= -1;
        }
        return fraction;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        float num;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            num = miniFloatFromString(s);
            if (num % Math.floor(num) == 0.0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get all valid bit sequences for miniFloat values.
     */
    private static String[] getValidMiniFloatBitSequences() {
        // MINI_FLOAT_SIZE = 8
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return result;
    }

    private static int MINI_FLOAT_SIZE = 8;
}
