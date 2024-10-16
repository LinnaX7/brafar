public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String output = "";
        int remainder;
        int sign = 1;
        if (num < 0) {
            sign = -1;
            num = num * -1;
        }
        while (num >= 7) {
            remainder = num % 7;
            num = num / 7;
            output = output + String.valueOf(remainder);
        }
        output = output + String.valueOf(num);
        String reverse_str = "";
        char temp;
        for (int i = 0; i < output.length(); i++) {
            temp = output.charAt(i);
            reverse_str = temp + reverse_str;
        }
        if (sign == -1) {
            reverse_str = '-' + reverse_str;
        }
        return reverse_str;
    }
}
