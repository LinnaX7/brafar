import java.util.*;

class Base7 {

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a number X: ");
        int num = sc.nextInt();
        System.out.println("Converted to Base 7: X = " + convertToBase7(num));
    }
}
