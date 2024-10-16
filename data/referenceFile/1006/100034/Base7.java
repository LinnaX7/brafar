import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int remains;
        String str = "";
        if (num >= 0) {
            int Rnum = num;
            while (Rnum >= 7) {
                remains = Rnum % 7;
                Rnum /= 7;
                str = String.valueOf(remains) + str;
            }
            str = String.valueOf(Rnum) + str;
        } else {
            String pos = String.valueOf(num);
            pos = pos.substring(1);
            int Rnum = 0;
            boolean na = false;
            int l = 1;
            int len2 = pos.substring(0).length();
            for (int i = pos.length() - 1; i >= 0; i--) {
                char current = pos.charAt(i);
                if (current == '-') {
                    na = true;
                    continue;
                }
                Rnum = Rnum + (current - '0') * l;
                l *= 10;
            }
            while (Rnum >= 7) {
                remains = Rnum % 7;
                Rnum /= 7;
                str = String.valueOf(remains) + str;
            }
            str = "-" + String.valueOf(Rnum) + str;
        }
        return str;
    }
}
