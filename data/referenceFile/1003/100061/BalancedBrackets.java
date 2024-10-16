public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '[' && str.charAt(i) != ']' && str.charAt(i) != '{' && str.charAt(i) != '}') {
                return false;
            }
        }
        if (str.length() % 2 != 0) {
            return false;
        }
        int num = str.length() / 2;
        int count = 0;
        boolean flag = false;
        for (int n = 0; n < num; n++) {
            flag = false;
            for (int m = n; m < str.length(); m++) {
                if (str.charAt(m) == '(' || str.charAt(m) == '[' || str.charAt(m) == '{') {
                    for (int x = str.length() - 1; x >= 0; x--) {
                        if ((int) str.charAt(x) - (int) str.charAt(m) == 1 || (int) str.charAt(x) - (int) str.charAt(m) == 2) {
                            if ((x - m) % 2 != 0) {
                                count++;
                                flag = true;
                                break;
                            }
                        }
                    }
                }
                if (flag) {
                    break;
                }
            }
        }
        if (count == num) {
            return true;
        }
        return false;
    }
}
