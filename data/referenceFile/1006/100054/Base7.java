import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        boolean judge = true;
        String empty = "";
        int consult = 0;
        if (num < 0) {
            num *= -1;
            judge = false;
        }
        while (num >= 7) {
            consult = num % 7;
            num = num / 7;
            empty = consult + empty;
        }
        empty = num + empty;
        if (judge == false) {
            empty = "-" + empty;
        }
        return empty;
    }
}
