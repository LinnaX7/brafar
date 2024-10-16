import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        /* Check if the string is empty. */
        if (str == null || str.length() == 0) {
            return false;
        }
        /* Check if the string only contains only the specific symbol. */
        int stringLength = str.length();
        char[] charStr = str.toCharArray();
        for (int i = 0; i < stringLength; i++) {
            if (charStr[i] != '(' && charStr[i] != ')' && charStr[i] != '[' && charStr[i] != ']' && charStr[i] != '{' && charStr[i] != '}') {
                return false;
            }
        }
        /* Check if the string is balance. */
        Stack stack = new Stack();
        for (int j = 0; j < stringLength; j++) {
            if (charStr[j] == '(' || charStr[j] == '[' || charStr[j] == '{') {
                stack.push(charStr[j]);
            }
            if (charStr[j] == ')') {
                if (!stack.pop().equals('(')) {
                    return false;
                }
            }
            if (charStr[j] == ']') {
                if (!stack.pop().equals('[')) {
                    return false;
                }
            }
            if (charStr[j] == '}') {
                if (!stack.pop().equals('{')) {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
