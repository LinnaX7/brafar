public class SpecialNumber {

    public static void main(String[] arg) {
        // System.out.print(isSpecial(210));
    }

    public static boolean isSpecial(int num) {
        // Task 3: Return true if and only if 'num' is special
        int count = 0;
        for (int i = 2; i * i < num; ++i) {
            if (num % i == 0) {
                ++count;
                if (count > 3) {
                    return false;
                }
                do {
                    num /= i;
                } while (num % i == 0);
            }
        }
        if (num != 1) {
            ++count;
        }
        if (count == 3) {
            return true;
        }
        return false;
    }
}
