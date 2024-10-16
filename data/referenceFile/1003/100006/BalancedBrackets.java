public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty()) {
            return false;
        }
        char[] temp = new char[str.length()];
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '(' && c != '[' && c != '{' && c != ')' && c != ']' && c != '}') {
                return false;
            }
            if (c == '[' || c == '(' || c == '{') {
                temp[j++] = c;
                continue;
            }
            if (j == 0) {
                return false;
            }
            if ((c == ']' && temp[j - 1] != '[') || (c == '}' && temp[j - 1] != '{') || (c == ')' && temp[j - 1] != '(')) {
                return false;
            }
            j--;
        }
        return j == 0;
    }
}
