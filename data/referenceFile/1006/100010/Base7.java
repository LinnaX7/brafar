import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String num7 = "";
        boolean isNegative = false;
        if (num < 0) {
            num *= -1;
            isNegative = true;
        } else {
            if (num == 0) {
                num7 += '0';
                return num7;
            }
        }
        while (num > 0) {
            num7 = num % 7 + num7;
            num /= 7;
        }
        if (isNegative == true) {
            num7 = '-' + num7;
        }
        return String.valueOf(num7);
    }
}
