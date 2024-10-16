public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        boolean negFlag = false;
        if (num < 0) {
            num = -num;
            negFlag = true;
        } else {
            if (num < 7) {
                return String.valueOf(num);
            }
        }
        int base = 7, count = 1;
        while (num / base > 0) {
            base *= 7;
            count++;
        }
        count--;
        base /= 7;
        String StrNum = new String("");
        while (base > 0) {
            StrNum += num / base;
            num = num % base;
            base /= 7;
        }
        if (negFlag) {
            return ("-" + StrNum);
        }
        return StrNum;
    }
}
