public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String strBase7 = "";
        boolean negative = false;
        if (num == 0) {
            return "0";
        }
        if (num < 0) {
            // Judge whether negative or positive & make num become abs(num)
            negative = true;
            num = -num;
        }
        while (num != 0) {
            // Find the num in base 7
            strBase7 += String.valueOf(num % 7);
            num /= 7;
        }
        String reverseBase7 = "";
        for (int i = strBase7.length() - 1; i >= 0; i--) {
            // Reverse the string
            reverseBase7 += strBase7.charAt(i);
        }
        // If negative then add "-"
        if (negative) {
            reverseBase7 = "-" + reverseBase7;
        }
        return reverseBase7;
    }
}
