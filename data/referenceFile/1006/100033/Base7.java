public class Base7 {

    public static String convertToBase7(int num) {
        int hold = 0;
        int remainder;
        boolean negative = false;
        if (num < 0) {
            negative = true;
            num *= -1;
        }
        for (int i = 1; num > 0; i *= 10) {
            remainder = num % 7;
            num /= 7;
            hold = hold + (i * remainder);
        }
        if (negative) {
            hold *= -1;
        }
        return hold + "";
    }
}
