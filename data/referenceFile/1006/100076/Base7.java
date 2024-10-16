public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        if (num == 0 || num == -0) {
            return "0";
        }
        int loopCount = 0;
        int temp = num;
        boolean negative = false;
        if (temp < 0) {
            temp = -temp;
            negative = true;
        }
        while (temp >= 1) {
            temp = temp / 7;
            loopCount++;
        }
        if (negative) {
            loopCount++;
            num = -num;
        }
        char[] container = new char[loopCount];
        int index = loopCount - 1;
        while (num >= 1) {
            int val = (num % 7) + 48;
            char digit = (char) val;
            container[index] = digit;
            index--;
            num = num / 7;
        }
        if (negative) {
            container[0] = '-';
        }
        String baseSeven = String.valueOf(container);
        return baseSeven;
    }
}
