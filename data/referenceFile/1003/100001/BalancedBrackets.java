public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty() || ((str.length() % 2) != 0)) {
            // non-empty & is balanced only when the length of str is even number//
            return false;
        }
        char[] bracket = str.toCharArray();
        for (char x : bracket) {
            if (!(x == '{' || x == '[' || x == '(' || x == '}' || x == ']' || x == ')')) {
                // contains only the six characters//
                return false;
            }
        }
        // loop through the str and delete the brackets
        while (str.contains("{}") || str.contains("[]") || str.contains("()")) {
            str = str.replaceAll("\\{\\}", "").replaceAll("\\[\\]", "").replaceAll("\\(\\)", "");
        }
        // if the length of str is 0 after deletion, it means there are only matching pairs of brackets that can be deleted and the str is balanced//
        if (str.length() == 0) {
            return true;
        }
        return false;
    }
}
