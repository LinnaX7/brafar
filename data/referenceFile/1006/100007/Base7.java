import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        boolean sign = num < 0;
        if (sign) {
            num *= -1;
        }
        int result = 0, b, i = 1;
        while (num != 0) {
            b = num % 7;
            result += b * i;
            i *= 10;
            num = num / 7;
            // TODO: Add your code here
        }
        if (sign) {
            result *= -1;
        }
        return (String.valueOf(result));
    }
}
