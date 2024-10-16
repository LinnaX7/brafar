public class Base7 {

    // Not to add other method
    public static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isPositive = true;
        if (num < 0) {
            isPositive = false;
            num = -num;
        }
        String res = "";
        int ones;
        int i = 1;
        while (num > 0) {
            ones = num / i;
            ones = (ones % 7);
            res = ones + res;
            num -= ones * i;
            i = i * 7;
        }
        return isPositive ? res : "-" + res;
    }
}
