import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        int i = 0;
        int output = 0;
        int n = num;
        int x = 0;
        while (abs(num) >= 7) {
            if (abs(num) >= 7) {
                n = num % 7;
                x = n;
                for (int a = 0; a < i; a++) {
                    x = x * 10;
                }
                output += x;
                num = num / 7;
                i++;
            }
        }
        for (int a = 0; a < i; a++) {
            num = num * 10;
        }
        output += num;
        return Integer.toString(output);
    }
}
