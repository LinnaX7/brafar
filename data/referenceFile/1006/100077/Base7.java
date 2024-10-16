import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        int flag = 1;
        String a = "";
        int temp = num;
        if (temp == 0) {
            a = "0";
            return a;
        }
        if (temp < 0) {
            flag = 0;
            temp *= -1;
        }
        while (temp != 0) {
            String s1 = String.valueOf(temp % 7);
            String s2 = s1 + a;
            a = s2;
            temp = temp / 7;
        }
        if (flag == 0) {
            String s1 = "-" + a;
            a = s1;
        }
        // TODO: Add your code here
        return a;
    }
}
