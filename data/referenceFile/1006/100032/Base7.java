import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        String result = "";
        boolean isNeg = false;
        if (num < 0) {
            isNeg = true;
            num = -num;
        }
        while (num > 0) {
            result = num % 7 + result;
            num /= 7;
        }
        if (isNeg) {
            result = '-' + result;
        }
        if (result == "") {
            result = "0";
        }
        return result;
    }
}
