import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int inputvalue) {
        int temp = inputvalue;
        int number;
        int n = 0;
        int x = 1;
        String s = "";
        if (inputvalue >= 0) {
            while (temp != 0) {
                number = temp % 7;
                temp = temp / 7;
                n = n + (number * x);
                x *= 10;
            }
            s = n + "";
        } else {
            s = "-" + convertToBase7(-inputvalue);
        }
        return s;
    }
}
/*public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int raw = scanner.nextInt();
            convertToBase7(raw);
        }
    }
}*/
