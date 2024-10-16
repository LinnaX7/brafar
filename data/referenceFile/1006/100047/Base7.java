import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        String seven_base = "";
        boolean neg_flag = num < 0;
        int digit = 0;
        do {
            digit = num % 7;
            seven_base = abs(digit) + seven_base;
            num /= 7;
        } while (num != 0);
        return neg_flag ? '-' + seven_base : seven_base;
    }
}
