import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        String ans;
        if (num < 0) {
            ans = "-";
            num = -num;
        } else {
            ans = "";
        }
        int up = 0;
        long[] pow = new long[30];
        pow[0] = 1;
        for (int i = 1; i <= 20; ++i) {
            pow[i] = pow[i - 1] * 7;
            if (pow[i] > num) {
                up = i - 1;
                break;
            }
        }
        for (int i = up; i >= 0; --i) {
            if (pow[i] > num) {
                ans = ans + 0;
                continue;
            }
            int tmp = (int) pow[i];
            int cur = num / tmp;
            ans = ans + cur;
            num -= cur * tmp;
        }
        return ans;
    }
}
