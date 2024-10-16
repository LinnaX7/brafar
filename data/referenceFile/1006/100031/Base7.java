import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int remainder = 0;
        boolean negative_point = false;
        String base7X = new String("");
        do {
            remainder = num % 7;
            // the integer X is a negative number, delete the '-' first to calculate, then add it at the end.
            if (num < 0) {
                remainder = -remainder;
                base7X = (char) (remainder + '0') + base7X;
                negative_point = true;
            } else {
                base7X = (char) (remainder + '0') + base7X;
            }
            num = num / 7;
        } while (num != 0);
        // check whether it is negative number and need to add the '-'
        if (negative_point == true) {
            base7X = '-' + base7X;
        }
        return base7X;
    }
}
