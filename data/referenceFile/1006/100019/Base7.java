import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num == 0) {
            return "0";
        }
        String ret = "";
        String sign = "-";
        boolean ifless0 = false;
        if (num < 0) {
            ifless0 = true;
            num = -num;
        }
        while (num != 0) {
            int remainder = num % 7;
            ret = (char) (remainder + 48) + ret;
            num = num / 7;
        }
        if (ifless0) {
            ret = sign + ret;
        }
        return ret;
    }
}
