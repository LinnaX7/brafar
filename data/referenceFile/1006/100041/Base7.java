import static java.lang.Math.abs;
import java.util.Scanner;

public class Base7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please input:");
        int input = Integer.parseInt(sc.next());
        String res = convertToBase7(input);
        System.out.println(res);
    }

    public static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        String res = "";
        int v = Math.abs(num);
        while (v != 0) {
            int i = v % 7;
            res = i + res;
            v /= 7;
        }
        if (num < 0) {
            return "-" + res;
        }
        return res;
    }
}
