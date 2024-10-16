import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        boolean isNegative = false;
        String answer = "";
        if (num < 0) {
            isNegative = true;
            num = abs(num);
        }
        while (num >= 7) {
            String remainder = String.format("%d", num % 7);
            num /= 7;
            answer = remainder + answer;
        }
        answer = String.format("%d", num) + answer;
        if (isNegative) {
            answer = "-" + answer;
        }
        return answer;
    }
}
