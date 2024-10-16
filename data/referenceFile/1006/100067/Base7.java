public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        String base7 = "";
        int a;
        int b = 1;
        int c = 0;
        int flag = 1;
        if (num < 0) {
            num = -num;
            flag = 0;
        }
        while (num >= 7) {
            a = num % 7;
            num = num / 7;
            c += a * b;
            b = b * 10;
        }
        c += num * b;
        base7 += flag == 1 ? c : -c;
        return base7;
    }
}
