public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        if (num < 0) {
            num = -num;
            sb.append('-');
        }
        int di = 1;
        while (di <= num) {
            di *= 7;
        }
        di /= 7;
        while (di > 0) {
            sb.append(num / di);
            num %= di;
            di /= 7;
        }
        return sb.toString();
    }
}
