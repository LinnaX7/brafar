public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int len = str.length();
        // if str is empty, return false
        if (len == 0) {
            return false;
        }
        char[] stack = new char[len];
        int top = -1;
        for (int i = 0; i < len; i++) {
            char curr = str.charAt(i);
            // if first stack element is not ({[, not balanced
            if (top == -1 && curr != '(' && curr != '{' && curr != '[') {
                return false;
            }
            if (curr == '(' || curr == '{' || curr == '[') {
                stack[++top] = curr;
            } else {
                if (curr == ')' && stack[top] == '(') {
                    top--;
                } else {
                    if (curr == '}' && stack[top] == '{') {
                        top--;
                    } else {
                        if (curr == ']' && stack[top] == '[') {
                            top--;
                        } else {
                            // if stack has other char or bracket mismatch, return false
                            return false;
                        }
                    }
                }
            }
        }
        // if stack still has leftover ({[, not balanced, return false
        if (top != -1) {
            return false;
        }
        return true;
    }
}
