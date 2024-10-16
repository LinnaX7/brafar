public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < (str.length()); i++) {
            str = str.replaceAll("\\(\\)", "");
            str = str.replaceAll("\\{\\}", "");
            str = str.replaceAll("\\[\\]", "");
        }
        return str.isEmpty();
    }
}
