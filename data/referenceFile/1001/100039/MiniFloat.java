import java.util.Scanner;

public class MiniFloat {

    public static float miniFloatFromString(String bitSequence) {
        // Task 1: compute the miniFloat value from "bitSequence";
        double exp;
        double man;
        float num;
        String sign = bitSequence.substring(0, 1);
        int signnum = Integer.valueOf(sign);
        String exp1 = bitSequence.substring(1, 2);
        int exp11 = Integer.valueOf(exp1);
        String exp2 = bitSequence.substring(2, 3);
        int exp22 = Integer.valueOf(exp2);
        String exp3 = bitSequence.substring(3, 4);
        int exp33 = Integer.valueOf(exp3);
        String exp4 = bitSequence.substring(4, 5);
        int exp44 = Integer.valueOf(exp4);
        String man1 = bitSequence.substring(5, 6);
        int man11 = Integer.valueOf(man1);
        String man2 = bitSequence.substring(6, 7);
        int man22 = Integer.valueOf(man2);
        String man3 = bitSequence.substring(7);
        int man33 = Integer.valueOf(man3);
        exp = (-exp11) * Math.pow(2, 3) + (exp22) * Math.pow(2, 2) + (exp33) * Math.pow(2, 1) + (exp44) * Math.pow(2, 0);
        man = 1 + (man11) * Math.pow(2, -1) + (man22) * Math.pow(2, -2) + (man33) * Math.pow(2, -3);
        if (signnum == 0) {
            num = (float) (man * Math.pow(2, exp));
        } else {
            num = -(float) (man * Math.pow(2, exp));
        }
        return num;
    }

    private static String[] getValidMiniFloatBitSequences() {
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return result;
    }

    private static int MINI_FLOAT_SIZE = 8;

    public static int numIntegralMiniFloats() {
        int count = 0;
        // Task 2: return the number of integral miniFloat values
        for (String s : getValidMiniFloatBitSequences()) {
            double getint = Math.floor(miniFloatFromString(s));
            if (miniFloatFromString(s) - getint == 0) {
                count += 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter 8 bits string: ");
        String str = input.next();
        System.out.println("The real int number is: " + miniFloatFromString(str));
        System.out.println("There are total " + numIntegralMiniFloats() + " integers.");
    }
}
