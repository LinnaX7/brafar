import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int temp = num;
        int digit;
        int n = 0;
        int x = 1;
        String s = "";
        if (num >= 0) {
            while (temp != 0) {
                digit = temp % 7;
                temp = temp / 7;
                n = n + (digit * x);
                x *= 10;
            }
            s = n + "";
        } else {
            s = "-" + convertToBase7(-num);
        }
        return s;
    }
    // public static void main(String[] args) {
    // String a = convertToBase7(-18);
    // System.out.println(a);
    // }
}
