public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        // Compute the base 7 number
        String ret = new String();
        int remainder = num;
        if (num > 0) {
            while (num > 0) {
                remainder = num % 7;
                ret += String.valueOf(remainder);
                num = num / 7;
            }
        } else {
            if (num == 0) {
                ret = "0";
            } else {
                if (num < 0) {
                    num *= -1;
                    while (num > 0) {
                        remainder = num % 7;
                        ret += String.valueOf(remainder);
                        num = num / 7;
                    }
                    ret += "-";
                }
            }
        }
        // reverse the index of char, giving the final result
        String result = new String();
        int n = ret.length();
        for (int i = 0; i < n; i++) {
            result += ret.charAt(n - i - 1);
        }
        return result;
    }
}
