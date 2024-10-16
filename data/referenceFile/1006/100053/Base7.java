import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        int Update_num = num, RemainderX = 0;
        boolean Mark_N = false;
        String S_Base7 = "";
        if (Update_num == 0) {
            return "0";
        }
        if (Update_num < 0) {
            Mark_N = true;
            Update_num = 0 - Update_num;
        }
        while (Update_num != 0) {
            RemainderX = Update_num % 7;
            S_Base7 = "" + RemainderX + S_Base7;
            Update_num /= 7;
        }
        if (Mark_N == true) {
            return "-" + S_Base7;
        }
        return S_Base7;
    }
}
