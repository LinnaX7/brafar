import java.util.Scanner;
import java.lang.String;

public class MiniFloat {

    public static void main(String[] args) {
        int number;
        System.out.println("Please enter the string: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        System.out.println("the float value is" + miniFloatFromString(str));
        System.out.println("the integral number in MiniFloat is: " + numIntegralMiniFloats());
    }

    public static float miniFloatFromString(String str) {
        float result;
        String x = str.substring(0, 1);
        String y = str.substring(2, 5);
        String f = str.substring(1, 2);
        String z = str.substring(5, 8);
        int exponent;
        if (f.equals("1")) {
            exponent = -8 + Integer.parseInt(y, 2);
        } else {
            exponent = Integer.parseInt(y, 2);
        }
        float mantissa = Integer.parseInt(z, 2) / 8.0f;
        result = (1 + mantissa) * (float) Math.pow(2, exponent);
        if (x.equals("1")) {
            result = result * -1;
        }
        return result;
    }

    static final double BIAS = 1e-6;

    static final int MINI_FLOAT_SIZE = 8;

    public static String[] getValidMiniFloatBitSequences() {
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return result;
    }

    public static int numIntegralMiniFloats() {
        int count = 0;
        for (String s : getValidMiniFloatBitSequences()) {
            float val = miniFloatFromString(s);
            if ((val - (int) val) < BIAS && (val - (int) val) > -BIAS) {
                count++;
            }
        }
        return count;
    }
}
