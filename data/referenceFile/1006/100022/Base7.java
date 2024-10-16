import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        // this part is to convert to hexadecimal
        String result = "";
        boolean sign = true;
        int tempNum1 = num;
        int tempNum2 = 0;
        if (tempNum1 > 0) {
            while (sign) {
                tempNum2 = tempNum1 % 7;
                result += tempNum2;
                tempNum1 = tempNum1 / 7;
                if (tempNum1 == 0) {
                    sign = false;
                }
            }
        } else {
            tempNum1 = tempNum1 * (-1);
            while (sign) {
                tempNum2 = tempNum1 % 7;
                result += tempNum2;
                tempNum1 = tempNum1 / 7;
                if (tempNum1 == 0) {
                    sign = false;
                }
            }
        }
        // this part is to convert the ordering of string
        char[] reverseChar = result.toCharArray();
        String finalString;
        // the Classification of different numbers
        if (num > 0) {
            char[] trueChar = new char[reverseChar.length];
            for (int j = reverseChar.length; j > 0; j--) {
                trueChar[trueChar.length - j] = reverseChar[j - 1];
            }
            finalString = new String(trueChar);
        } else {
            if (num < 0) {
                char[] trueChar = new char[reverseChar.length + 1];
                for (int j = reverseChar.length; j > 0; j--) {
                    // there is the basic question,why not "trueChar.length-j+1" ?
                    trueChar[trueChar.length - j] = reverseChar[j - 1];
                }
                trueChar[0] = '-';
                finalString = new String(trueChar);
            } else {
                finalString = "0";
            }
        }
        return finalString;
    }
}
