public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num == 0) {
            return "0";
        }
        int digit;
        int result = 0;
        int exp = 0;
        String res = "";
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num = -num;
        }
        while (num != 0) {
            digit = num % 7;
            num /= 7;
            exp++;
            char c = (char) ('0' + digit);
            res = c + res;
        }
        if (negative) {
            res = '-' + res;
        }
        return res;
    }
}
