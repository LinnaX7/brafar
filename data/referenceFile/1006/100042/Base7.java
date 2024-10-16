import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        boolean isNegative = false;
        String result = "";
        if (num == 0) {
            return "0";
        }
        if (num < 0) {
            isNegative = true;
            num = -num;
        }
        while (num != 0) {
            result = Math.abs(num % 7) + result;
            num /= 7;
        }
        if (isNegative) {
            result = "-" + result;
        }
        return result;
    }
}
