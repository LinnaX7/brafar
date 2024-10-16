import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        boolean negNum = false;
        int reminder = 0;
        int counter = 0;
        int temp = 1;
        if (num < 0) {
            negNum = true;
        }
        if (negNum) {
            num = num * (-1);
        }
        while (num > 0) {
            for (int i = 0; i < counter; i++) {
                temp *= 10;
            }
            reminder += (num % 7) * temp;
            num /= 7;
            counter++;
            temp = 1;
        }
        if (negNum) {
            return "-" + String.valueOf(reminder);
        }
        return String.valueOf(reminder);
    }
}
