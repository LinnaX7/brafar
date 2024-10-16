public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        int num = Integer.parseInt(bitSequence);
        int counter = 0;
        float base = (float) 0.125;
        float significand = 1;
        int exponent = 0;
        while (num > 11) {
            counter++;
            if ((num % 10 != 0) & (counter < 4)) {
                significand += base;
                base *= 2;
                num--;
                num = num / 10;
            } else {
                if ((num % 10 == 0) & (counter < 4)) {
                    base *= 2;
                    num = num / 10;
                } else {
                    if ((num % 10 != 0) & (counter >= 4)) {
                        exponent += Math.pow(2, counter - 4);
                        num--;
                        num = num / 10;
                    } else {
                        if ((num % 10 == 0) & (counter >= 4)) {
                            num = num / 10;
                        }
                    }
                }
            }
        }
        if ((num - 10 == 0) & (counter == 0)) {
            significand += 0.25;
        }
        if ((num - 10 == 0) & (counter == 1)) {
            significand += 0.5;
        }
        if ((num - 10 == 0) & (counter == 2)) {
            exponent += 1;
        }
        if ((num - 10 == 0) & (counter == 3)) {
            exponent += 2;
        }
        if ((num - 10 == 0) & (counter == 4)) {
            exponent += 4;
        }
        if ((num - 10 == 0) & (counter == 5)) {
            exponent -= 8;
        }
        if ((num - 10 == 0) & (counter == 6)) {
            significand *= -1;
        }
        if ((num - 11 == 0) & (counter == 0)) {
            significand += 0.125;
            significand += 0.25;
        }
        if ((num - 11 == 0) & (counter == 1)) {
            significand += 0.25;
            significand += 0.5;
        }
        if ((num - 11 == 0) & (counter == 2)) {
            significand += 0.5;
            exponent += 1;
        }
        if ((num - 11 == 0) & (counter == 3)) {
            exponent += 1;
            exponent += 2;
        }
        if ((num - 11 == 0) & (counter == 4)) {
            exponent += 2;
            exponent += 4;
        }
        if ((num - 11 == 0) & (counter == 5)) {
            exponent += 4;
            exponent -= 8;
        }
        if ((num - 11 == 0) & (counter == 6)) {
            exponent -= 8;
            significand *= -1;
        }
        significand = (float) (significand * Math.pow(2, exponent));
        return significand;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat value;
        int i;
        int counter = 0;
        int answer;
        int exponent;
        for (i = 0; i <= 7; i++) {
            exponent = -8;
            while (exponent <= 7) {
                if (((1 + 0.125 * i) * Math.pow(2, exponent)) % 1 == 0)
                    counter++;
                exponent++;
            }
        }
        answer = counter * 2;
        return answer;
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
