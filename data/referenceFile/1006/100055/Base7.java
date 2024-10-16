public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num == 0) {
            return "0";
        }
        boolean neg = false;
        if (num < 0) {
            neg = true;
        }
        int count = 0;
        int currNum = num;
        if (neg) {
            currNum *= -1;
            num *= -1;
        }
        while (currNum > 0) {
            currNum /= 7;
            count++;
        }
        int[] reInt = new int[count];
        String s = "";
        for (int i = count - 1; i >= 0; i--) {
            reInt[i] = num % 7;
            s = Integer.toString(reInt[i]) + s;
            num /= 7;
        }
        if (neg) {
            s = '-' + s;
        }
        return s;
    }
}
