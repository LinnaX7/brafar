import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int isNegative = 0;
        if (num < 0) {
            num *= -1;
            isNegative = 1;
        }
        int copyNum = num;
        String ans = "";
        int tryIndex = 0;
        double tryNum = 0;
        int i;
        while (tryNum <= copyNum) {
            for (i = 0; i <= 6; i++) {
                tryNum = i;
                for (int j = 0; j <= tryIndex - 1; j++) {
                    tryNum = tryNum * 7;
                }
                if (tryNum > copyNum) {
                    if (i == 0) {
                        i = 6;
                        tryIndex -= 1;
                    } else {
                        i--;
                    }
                    ans = ans + (char) (i + '0');
                    for (int j = 0; j <= tryIndex - 1; j++) {
                        i = i * 7;
                    }
                    copyNum -= i;
                    break;
                }
            }
            if (tryNum < copyNum) {
                tryIndex++;
            }
        }
        int k;
        for (int j = tryIndex - 1; j >= 0; j--) {
            for (k = 0; k <= 6; k++) {
                tryNum = k;
                for (int p = 0; p <= j - 1; p++) {
                    tryNum = tryNum * 7;
                }
                if (tryNum > copyNum) {
                    k--;
                    ans = ans + (char) (k + '0');
                    for (int p = 0; p <= j - 1; p++) {
                        k = k * 7;
                    }
                    copyNum -= k;
                    break;
                }
            }
        }
        if (isNegative == 1) {
            ans = '-' + ans;
        }
        return ans;
    }
}
