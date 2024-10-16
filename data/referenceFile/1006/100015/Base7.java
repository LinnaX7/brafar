public class Base7 {

    public static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder RemainderArray = new StringBuilder();
        boolean flag = false;
        if (num < 0) {
            flag = true;
            num = Math.abs(num);
        }
        while (num != 0) {
            RemainderArray.append(num % 7);
            num = num / 7;
        }
        if (flag) {
            RemainderArray.append("-");
        }
        return RemainderArray.reverse().toString() + "";
        // TODO: Add your code here
    }
}
