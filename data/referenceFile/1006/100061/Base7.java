import java.util.SplittableRandom;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String reverse = "", sign = "";
        int remainder;
        if (num == 0) {
            reverse = "0";
        } else {
            if (num < 0) {
                sign = "-";
                // Pass a positive int to the loop
                num = -1 * num;
            }
        }
        while (num != 0) {
            remainder = num % 7;
            num = num / 7;
            // Inverted string concatenation
            reverse = remainder + reverse;
        }
        reverse = sign + reverse;
        return reverse;
    }
}
