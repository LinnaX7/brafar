public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int negative = 0;
        if (num < 0) {
            num *= -1;
            negative = 1;
        }
        int ans = 0;
        int i = 0;
        int test = 1;
        while (test <= num) {
            test *= 7;
            i++;
        }
        i--;
        int ind, ti;
        while (num > 0) {
            ind = 1;
            ti = 1;
            for (int j = 0; j < i; j++) {
                ind *= 7;
                ti *= 10;
            }
            ans += num / ind * ti;
            num = num - num / ind * ind;
            i--;
        }
        if (negative == 1) {
            ans *= -1;
        }
        return String.valueOf(ans);
    }
}
