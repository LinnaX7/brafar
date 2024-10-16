import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num < 0) {
            return '-' + convertToBase7(-num);
        }
        if (num < 7) {
            return num + "";
        }
        return convertToBase7(num / 7) + num % 7;
    }
}
