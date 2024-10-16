public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty()) {
            return false;
        }
        while (str.contains("()") || str.contains("[]") || str.contains("{}")) {
            if (str.contains("()")) {
                str = str.replace("()", "");
            }
            if (str.contains("[]")) {
                str = str.replace("[]", "");
            }
            if (str.contains("{}")) {
                str = str.replace("{}", "");
            }
        }
        return str.isEmpty();
    }
}
