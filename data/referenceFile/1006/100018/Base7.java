import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        String base = "";
        boolean negative = false;
        if (num < 0) {
            num = -num;
            negative = true;
        }
        while (num >= 7) {
            int remain = num % 7;
            base = (char) (remain + '0') + base;
            num /= 7;
        }
        base = (char) (num + '0') + base;
        if (negative) {
            base = '-' + base;
        }
        return base;
    }
}
