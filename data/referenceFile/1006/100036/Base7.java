import java.util.Scanner;
import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        if (num < 7) {
            return Integer.toString(num);
        }
        return convertToBase7(num / 7) + Integer.toString(num % 7);
    }

    public static void main(String[] args) {
        Scanner answer = new Scanner(System.in);
        int num = answer.nextInt();
        System.out.println(convertToBase7(num));
    }
}
