// Question 3
public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int base = 1, result = 0;
        while (num != 0) {
            result += base * (num % 7);
            num /= 7;
            base *= 10;
        }
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        int num = 100;
        System.out.println(convertToBase7(num));
    }
}
