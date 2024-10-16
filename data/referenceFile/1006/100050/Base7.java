public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num = -num;
        }
        String num_base7_before = "";
        while (num >= 7) {
            num_base7_before += num % 7;
            num = num / 7;
        }
        num_base7_before += num % 7;
        if (negative == true) {
            num_base7_before += "-";
        }
        String num_base7_after = "";
        for (int i = num_base7_before.length() - 1; i >= 0; i--) {
            num_base7_after += num_base7_before.charAt(i);
        }
        return num_base7_after;
    }
}
