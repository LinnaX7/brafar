import java.util.Scanner;

class Base7 {

    public static String convertToBase7(int number) {
        if (number < 0) {
            return "-" + convertToBase7(-number);
        }
        if (number < 7) {
            return Integer.toString(number);
        }
        return convertToBase7(number / 7) + Integer.toString(number % 7);
    }

    public static void main(String[] args) {
        System.out.print("please enter a number:");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        System.out.print(convertToBase7(number));
    }
}
