public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int sign = 0;
        int temp, remainder;
        String strR = "";
        if (num < 0) {
            sign = -1;
            num = -num;
        }
        while (num >= 7) {
            temp = num / 7;
            remainder = num % 7;
            strR += remainder;
            num = temp;
        }
        strR += num;
        String str = "";
        if (sign == -1) {
            str += "-";
        }
        for (int i = 0; i < strR.length(); i++) {
            str += strR.charAt(strR.length() - 1 - i);
        }
        return str;
    }
}
