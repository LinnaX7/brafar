import java.util.Scanner;

public class MiniFloat {

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence"
        double exponent;
        double mantissa;
        float number;
        String sign = bitSequence.substring(0, 1);
        int signnum = Integer.valueOf(sign);
        String mantissa_1 = bitSequence.substring(5, 6);
        int mantissa1 = Integer.valueOf(mantissa_1);
        String mantissa_2 = bitSequence.substring(6, 7);
        int mantissa2 = Integer.valueOf(mantissa_2);
        String mantissa_3 = bitSequence.substring(7);
        int mantissa3 = Integer.valueOf(mantissa_3);
        String exponent_1 = bitSequence.substring(1, 2);
        int exponent1 = Integer.valueOf(exponent_1);
        String exponent_2 = bitSequence.substring(2, 3);
        int exponent2 = Integer.valueOf(exponent_2);
        String exponent_3 = bitSequence.substring(3, 4);
        int exponent3 = Integer.valueOf(exponent_3);
        String exponent_4 = bitSequence.substring(4, 5);
        int exponent4 = Integer.valueOf(exponent_4);
        exponent = (-exponent1) * Math.pow(2, 3) + (exponent2) * Math.pow(2, 2) + (exponent3) * Math.pow(2, 1) + (exponent4) * Math.pow(2, 0);
        mantissa = 1 + (mantissa1) * Math.pow(2, -1) + (mantissa2) * Math.pow(2, -2) + (mantissa3) * Math.pow(2, -3);
        if (signnum != 0) {
            number = -(float) (mantissa * Math.pow(2, exponent));
        } else {
            number = (float) (mantissa * Math.pow(2, exponent));
        }
        return number;
    }

    private static int MINI_FLOAT_SIZE = 8;

    private static String[] getValidMiniFloatBitSequences() {
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] output = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            output[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return output;
    }

    public static int numIntegralMiniFloats() {
        int num = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            double get_integer = Math.floor(miniFloatFromString(s));
            if (miniFloatFromString(s) - get_integer == 0) {
                num += 1;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter 8 bits string: ");
        String str = input.next();
        System.out.println("The real int number is: " + miniFloatFromString(str));
        System.out.println("There are total " + numIntegralMiniFloats() + " integers.");
    }
}
