import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        boolean isNeg = num < 0;
        if (isNeg) {
            num = -num;
        }
        String str = "";
        do {
            str = num % 7 + str;
            num /= 7;
        } while (num > 0);
        return isNeg ? "-" + str : str;
    }

    public static void main(String[] args) {
        System.out.println(convertToBase7(-1234567890));
    }
}
