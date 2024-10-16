import java.util.Scanner;

class Base7 {

    public static String convertToBase7(int num) {
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        if (num < 7) {
            return Integer.toString(num);
        }
        return convertToBase7(num / 7) + convertToBase7(num % 7);
    }

    public static void main(String[] args) {
        System.out.println("Please input a number :");
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        System.out.print(convertToBase7(num));
    }
}
