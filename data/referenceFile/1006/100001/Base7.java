import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int Base7 = 0;
        int exponent = 0;
        while (num != 0) {
            int Rem = num % 7;
            num = num / 7;
            Base7 += Rem * Math.pow(10, exponent);
            exponent++;
        }
        return Base7 + "";
    }
}
