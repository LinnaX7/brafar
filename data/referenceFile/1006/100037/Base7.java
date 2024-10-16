import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String result = "";
        if (num >= 0) {
            while (num >= 7) {
                int reminder = 0;
                reminder = num % 7;
                result = reminder + result;
                num = num / 7;
            }
        } else {
            while (num <= -7) {
                int negReminder = 0;
                negReminder = num % 7;
                int traReminder = negReminder - (negReminder * 2);
                result = traReminder + result;
                num = num / 7;
            }
        }
        result = num + result;
        return result;
    }
}
