import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        int a = 0;
        int b = 0;
        while (num != 0) {
            int c = num % 7;
            b += c * (int) Math.pow(10, a);
            a++;
            num /= 7;
        }
        return Integer.toString(b);
    }
}
