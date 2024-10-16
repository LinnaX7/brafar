import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String base7string;
        int base7int = 0;
        int multiple = 1;
        while (num != 0) {
            base7int += (num % 7) * multiple;
            num /= 7;
            multiple *= 10;
        }
        base7string = String.valueOf(base7int);
        return base7string;
    }
}
