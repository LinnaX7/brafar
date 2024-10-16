import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int flag = 1;
        if (num < 0) {
            flag = -1;
            num = -num;
        }
        if (num == 0) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (num != 0) {
            stringBuilder.append(num % 7);
            num /= 7;
        }
        String ans = stringBuilder.reverse().toString();
        if (flag == -1) {
            ans = "-" + ans;
        }
        return ans;
    }
}
