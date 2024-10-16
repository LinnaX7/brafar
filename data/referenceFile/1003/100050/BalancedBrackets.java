import java.util.Stack;

public class BalancedBrackets {

    public static char pair_char(char ch) {
        if (ch == '(')
            return ')';
        if (ch == '[')
            return ']';
        if (ch == '{')
            return '}';
        return 0;
    }

    public static boolean is_left(char ch) {
        return (ch == '(' || ch == '[' || ch == '{');
    }

    public static boolean is_right(char ch) {
        return (ch == ')') || (ch == ']') || (ch == '}');
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str == null || str.isEmpty()) {
            return false;
        }
        char[] s = str.toCharArray();
        Stack<Character> stk = new Stack<>();
        for (char c : s) {
            if (is_left(c)) {
                stk.push(c);
            } else {
                if (is_right(c)) {
                    if (stk.isEmpty()) {
                        return false;
                    }
                    char match = pair_char(stk.peek());
                    if (match != c) {
                        return false;
                    }
                    stk.pop();
                } else {
                    return false;
                }
            }
        }
        return stk.isEmpty();
    }
}
