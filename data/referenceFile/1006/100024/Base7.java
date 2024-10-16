import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        String temp = "";
        String reverse = "";
        // TODO: Add your code here
        if (num > 0) {
            while (num > 0) {
                int s = num % 7;
                num = num / 7;
                temp += Integer.toString(s);
            }
            for (int i = temp.length() - 1; i >= 0; i--) {
                reverse += temp.substring(i, i + 1);
            }
        } else {
            if (num == 0) {
                return "0";
            }
            if (num < 0) {
                return "-" + convertToBase7(-num);
            }
        }
        return reverse;
    }
}
