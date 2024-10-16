import java.util.Scanner;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        if (num < 7) {
            return String.valueOf(num);
        }
        return convertToBase7(num / 7) + String.valueOf(num % 7);
    }

    public static void main(String[] args) {
        // test
        System.out.println(convertToBase7(100));
    }
}
