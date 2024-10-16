public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        float miniFloat = (float) 1.0;
        float value = (float) 0.125;
        int temp = Integer.parseInt(bitSequence);
        int count = 0;
        int exponent = 0;
        while (temp > 11) {
            ++count;
            if ((count == 2) | (count == 3)) {
                value *= 2;
            }
            if ((temp % 10 == 1) & (count <= 3)) {
                miniFloat += value;
                temp -= 1;
            } else {
                if ((temp % 10 == 1) & (count >= 4)) {
                    exponent += Math.pow(2, count - 4);
                    temp -= 1;
                }
            }
            temp /= 10;
        }
        if (temp == 10) {
            if (count == 0) {
                miniFloat += 0.25;
            } else {
                if (count == 1) {
                    miniFloat += 0.5;
                } else {
                    if (count == 2) {
                        exponent += 1;
                    } else {
                        if (count == 3) {
                            exponent += 2;
                        } else {
                            if (count == 4) {
                                exponent += 4;
                            } else {
                                if (count == 5) {
                                    exponent -= 8;
                                } else {
                                    if (count == 6) {
                                        miniFloat *= -1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (temp == 11) {
            if (count == 0) {
                miniFloat += 0.125;
                miniFloat += 0.25;
            } else {
                if (count == 1) {
                    miniFloat += 0.25;
                    miniFloat += 0.5;
                } else {
                    if (count == 2) {
                        miniFloat += 0.5;
                        exponent += 1;
                    } else {
                        if (count == 3) {
                            exponent += 1;
                            exponent += 2;
                        } else {
                            if (count == 4) {
                                exponent += 2;
                                exponent += 4;
                            } else {
                                if (count == 5) {
                                    exponent += 4;
                                    exponent -= 8;
                                } else {
                                    if (count == 6) {
                                        exponent -= 8;
                                        miniFloat *= -1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        miniFloat = (float) (miniFloat * Math.pow(2, exponent));
        return miniFloat;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int i;
        int exponent = -8, count = 0;
        for (i = 0; i <= 7; i++) {
            exponent = -8;
            while (exponent <= 7) {
                if (((1 + 0.125 * i) * Math.pow(2, exponent)) % 1 == 0)
                    ++count;
                ++exponent;
            }
        }
        // because we ignore the sign
        count *= 2;
        System.out.println(count);
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
}
