import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int l = str.length();
        Stack<Character> stack = new Stack<Character>();
        if (l == 0) {
            return false;
        }
        for (int i = 0; i < l; i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        return false;
                    }
                } else {
                    if (c == ']') {
                        if (stack.isEmpty() || stack.pop() != '[') {
                            return false;
                        }
                    } else {
                        if (c == '}') {
                            if (stack.isEmpty() || stack.pop() != '{') {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
