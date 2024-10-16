import java.util.*;

public class Base7 {

    public static String convertToBase7(int num) {
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        if (num < 7) {
            return Integer.toString(num);
        }
        return convertToBase7(num / 7) + Integer.toString(num % 7);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        System.out.print(convertToBase7(num));
    }
}
