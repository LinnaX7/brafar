import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        boolean ifNeg = false;
        String output = "";
        if (num == 0) {
            output = "0";
        }
        if (num < 0) {
            ifNeg = true;
            num *= -1;
        }
        while (num != 0) {
            output = num % 7 + output;
            num = num / 7;
        }
        if (ifNeg == true) {
            output = "-" + output;
        }
        return output;
    }
}
