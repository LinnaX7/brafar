public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String ans = "";
        int num_clone = num;
        if (num_clone >= 0) {
            while (num_clone > 6) {
                ans = String.valueOf(num_clone % 7) + ans;
                num_clone = num_clone / 7;
            }
            ans = String.valueOf(num_clone % 7) + ans;
            return ans;
        }
        num_clone = -num_clone;
        while (num_clone > 6) {
            ans = String.valueOf(num_clone % 7) + ans;
            num_clone = num_clone / 7;
        }
        ans = "-" + String.valueOf(num_clone % 7) + ans;
        return ans;
    }
}
