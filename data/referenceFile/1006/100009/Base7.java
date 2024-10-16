import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int fi, se = 1, temp;
        String str = "";
        String a = "";
        boolean x;
        if (num >= 0) {
            temp = num;
        } else {
            temp = -num;
            a += "-";
        }
        do {
            fi = temp % 7;
            se = temp / 7;
            temp = se;
            str += fi;
        } while (se != 0);
        for (int i = str.length(); i > 0; i--) {
            a += str.substring(i - 1, i);
        }
        return a;
    }
}
