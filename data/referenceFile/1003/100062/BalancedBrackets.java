public class BalancedBrackets {

    static String[] brackets = { "{", "}", "[", "]", "(", ")" };

    static String[] str;

    // constructor
    public BalancedBrackets() {
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("({)[]}"));
    }

    // for testing
    public static boolean isBalanced(String test) {
        // 1. check the size
        str = test.split("");
        if (str.length < 1) {
            return false;
        }
        if (str.length == 1 || str.length == 3 || str.length == 5) {
            return false;
        }
        if (str.length == 2) {
            if (isOpenBrackets(str[1])) {
                return false;
            }
        }
        // 2. other inputs?
        for (int i = 0; i < str.length; i++) {
            if (inBrackets(str[i]) == 7) {
                return false;
            }
        }
        // 
        if (!(isOpenBrackets(str[0]))) {
            return false;
        }
        if ((isOpenBrackets(str[0])) && (isOpenBrackets(str[1]))) {
            if (isOpenBrackets(str[2])) {
                int temp1 = inBrackets(str[2]);
                if (str[3].equals(brackets[temp1 + 1])) {
                    return true;
                }
            } else {
                if (str.length == 6) {
                    int temp2 = inBrackets(str[2]);
                    if (!(brackets[temp2 - 1].equals(str[1]))) {
                        return false;
                    }
                    if (!(isOpenBrackets(str[3]))) {
                        return false;
                    }
                } else {
                    if (str.length == 4) {
                        int temp3 = inBrackets(str[1]);
                        if (!(brackets[temp3 + 1].equals(str[2]))) {
                            return false;
                        }
                    }
                }
            }
        } else {
            int temp4 = inBrackets(str[1]);
            if (!(brackets[temp4 - 1].equals(str[0]))) {
                return false;
            }
        }
        return true;
    }

    public static int inBrackets(String s) {
        for (int i = 0; i < 6; i++) {
            if (s.equals(brackets[i]))
                return i;
        }
        return 7;
    }

    public static boolean isOpenBrackets(String t) {
        for (int g = 0; g < 6; g += 2) {
            if (t.equals(brackets[g]))
                return true;
        }
        return false;
    }
}
