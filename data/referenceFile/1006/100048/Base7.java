public class Base7 {

    public static String convertToBase7(int value) {
        // TODO: Add your code here
        // 0, special case : 0;
        if (value == 0) {
            return "0";
        }
        // 1, check whether is negative -- negative
        boolean negative = false;
        if (value < 0) {
            negative = true;
            value = -value;
        }
        // 2, convert to base-7 String
        String result = "";
        int quotient, remainder;
        while (value > 0) {
            quotient = value / 7;
            remainder = value - quotient * 7;
            // :( cannot use StringBuider --inefficient implement  (with n^n space complexity?)
            result = (char) (remainder + '0') + result;
            value = quotient;
        }
        if (negative) {
            result = '-' + result;
        }
        return result;
    }
}
