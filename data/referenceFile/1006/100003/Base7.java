public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num == 0) {
            return "0";
        }
        String result = "";
        int absNum = Math.abs(num);
        while (absNum != 0) {
            int remainder = absNum % 7;
            String x = Integer.toString(remainder);
            result = x + result;
            absNum /= 7;
        }
        if (num < 0) {
            return "-" + result;
        }
        return result;
    }
}
