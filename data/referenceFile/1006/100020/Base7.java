import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        int abs = num < 0 ? -1 * num : num;
        String output = "";
        String sb = "";
        while (abs >= 7) {
            sb = sb + (abs % 7);
            abs /= 7;
        }
        sb = sb + abs;
        for (int i = sb.length() - 1; i >= 0; i--) {
            output = output + sb.charAt(i);
        }
        return num >= 0 ? output.toString() : "-" + output.toString();
    }

    // TODO: Add your code here
    public static void main(String[] args) {
        System.out.println(Base7.convertToBase7(7));
    }
}
