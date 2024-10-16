public class MiniFloat {

    public static void main(String[] args) {
        System.out.println(numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        double value;
        boolean error = false;
        String p = "0";
        String q = "1";
        if (bitSequence.length() != 8) {
            System.out.println("The String should be formed of 8 binary digits");
            return 0;
        }
        for (int i = 0; i < bitSequence.length(); i++) {
            if ((!Character.isDigit(bitSequence.charAt(i))) || (!p.equals(String.valueOf(bitSequence.charAt(i))) && !q.equals(String.valueOf(bitSequence.charAt(i))))) {
                error = true;
                break;
            }
        }
        if (error == true) {
            System.out.println("The String should be formed of 8 binary digits");
            return 0;
        }
        int isPositive = Integer.parseInt(String.valueOf(bitSequence.charAt(0)));
        String exponent = bitSequence.substring(1, 5);
        int intexponent = 0;
        String mantissa = bitSequence.substring(5);
        float floatmantissa = 0;
        for (int x = 1; x < exponent.length(); x++) {
            intexponent += Math.pow(2, (exponent.length() - x - 1)) * Integer.parseInt(String.valueOf(exponent.charAt(x)));
        }
        // System.out.println(intexponent);
        for (int z = 0; z < mantissa.length(); z++) {
            floatmantissa += Math.pow(2, -z - 1) * Integer.parseInt(String.valueOf(mantissa.charAt(z)));
        }
        floatmantissa += 1;
        if (q.equals(String.valueOf(exponent.charAt(0)))) {
            value = floatmantissa * (Math.pow(2, intexponent - 8));
        } else {
            value = floatmantissa * (Math.pow(2, intexponent));
        }
        float f = (float) value;
        if (isPositive == 0) {
            return f;
        }
        f = (-1) * f;
        return f;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        float temp;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            temp = miniFloatFromString(s);
            if (temp == (int) temp) {
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
