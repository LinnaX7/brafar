public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        String openParenthese = "{[(";
        String closeParenthese = "}])";
        if (str.length() % 2 != 0 || str.length() == 0) {
            return false;
        }
        if (!exist(openParenthese, str.charAt(0))) {
            return false;
        }
        int l = str.length() / 2;
        for (int k = 1; k <= l; k++) {
            for (int i = 0; i < str.length() - 1; i++) {
                if (!exist(openParenthese, str.charAt(i)) && !exist(closeParenthese, str.charAt(i))) {
                    // 'str' contains other characters
                    return false;
                }
                if (exist(openParenthese, str.charAt(i))) {
                    int idx = openParenthese.indexOf(str.charAt(i));
                    if (str.charAt(i + 1) == closeParenthese.charAt(idx)) {
                        str = deleteParenthese(str, i);
                    }
                }
            }
        }
        return str.length() == 0;
    }

    public static boolean exist(String str, char chr) {
        // check whether a string contains a char
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == chr) {
                return true;
            }
        }
        return false;
    }

    public static String deleteParenthese(String str, int i) {
        return str.substring(0, i) + str.substring(i + 2);
    }
}
