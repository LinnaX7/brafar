import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int number = num;
        if (number == 0) {
            return "0";
        }
        if (number < 0) {
            number = -number;
        }
        String result = "";
        while (number > 0) {
            result = (char) (number % 7 + '0') + result;
            number /= 7;
        }
        if (num < 0) {
            result = "-" + result;
        }
        return result;
    }
}
