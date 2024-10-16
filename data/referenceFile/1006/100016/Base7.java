import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int res = 0, poww = 1;
        while (num != 0) {
            int reminder = num % 7;
            res += reminder * poww;
            num /= 7;
            poww *= 10;
        }
        return String.format("%d", res);
    }
}
