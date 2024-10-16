import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String str = "";
        boolean isLessThanZero = num < 0 ? true : false;
        num = Math.abs(num);
        int x = num % 7;
        int y = num / 7;
        str += x;
        while (y != 0) {
            x = y % 7;
            y = y / 7;
            str = x + str;
        }
        return isLessThanZero ? "-" + str : str;
    }
}
