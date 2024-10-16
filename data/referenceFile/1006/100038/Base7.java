public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num == 0) {
            return "0";
        }
        // like binary conversion from semester one but base-7 instead of base-2
        boolean neg;
        String result = "";
        // check if num is positive or negative
        if (num >= 0) {
            neg = false;
        } else {
            neg = true;
            num *= -1;
        }
        // base-7 calculation
        while (num != 0) {
            result = String.valueOf(num % 7) + result;
            num /= 7;
        }
        // adds negative symbol if num is originally negative
        if (neg) {
            result = '-' + result;
        }
        return result;
    }
}
