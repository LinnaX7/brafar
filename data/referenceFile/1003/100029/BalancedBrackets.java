public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // if 0 length false
        if (str.length() == 0) {
            return false;
        }
        // if odd length false
        if (str.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '{' && str.charAt(i) != '}' && str.charAt(i) != '[' && str.charAt(i) != ']' && str.charAt(i) != '(' && str.charAt(i) != ')') {
                return false;
            }
        }
        // using string methods contains and replaceALL
        // if str contains [] or () or {} then loop goes working
        // after it replaces all brackets to zero length string
        while (str.contains("[]") || str.contains("()") || str.contains("{}")) {
            str = str.replaceAll("\\[]", "");
            str = str.replaceAll("\\(\\)", "");
            str = str.replaceAll("\\{}", "");
        }
        // if length 0 then true
        if (str.length() == 0) {
            return true;
        }
        return false;
    }
}
