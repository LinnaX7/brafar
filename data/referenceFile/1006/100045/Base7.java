import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String retStr = "";
        String reverseStr = "";
        boolean isPositive = num >= 0;
        int r = 0;
        int n = isPositive ? num : -num;
        while (n / 7 != 0) {
            r = n % 7;
            n = n / 7;
            reverseStr += (char) ('0' + r);
        }
        reverseStr += (char) ('0' + n);
        if (!isPositive) {
            retStr += '-';
        }
        for (int i = reverseStr.length() - 1; i >= 0; i--) {
            retStr += reverseStr.charAt(i);
        }
        return retStr;
    }
}
