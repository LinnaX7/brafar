import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String base7 = "";
        int quotient = num;
        if (num < 0) {
            quotient = abs(num);
        }
        while (quotient > 6) {
            base7 += quotient % 7;
            quotient = quotient / 7;
        }
        base7 += quotient % 7;
        String temp = "";
        for (int i = base7.length(); i > 0; i--) {
            temp += base7.charAt(i - 1);
        }
        if (num < 0) {
            temp = "-" + temp;
        }
        return temp;
    }
}
