public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        String strall = "(){}[]";
        String pairstr1 = "()", pairstr2 = "{}", pairstr3 = "[]";
        if (str.length() % 2 != 0) {
            return false;
        }
        if (str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!strall.contains(String.valueOf(str.charAt(i)))) {
                return false;
            }
        }
        int len = str.length() / 2;
        for (int z = 0; z < len; z++) {
            if (str.contains(pairstr1)) {
                str = str.replace(pairstr1, "");
            }
            if (str.contains(pairstr2)) {
                str = str.replace(pairstr2, "");
            }
            if (str.contains(pairstr3)) {
                str = str.replace(pairstr3, "");
            }
        }
        if (str.length() == 0) {
            return true;
        }
        return false;
    }
}
