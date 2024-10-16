import java.util.*;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str == "" || str == null) {
            return false;
        }
        Stack<Character> stacka = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '{' && c != '}' && c != '(' && c != ')' && c != '[' && c != ']') {
                return false;
            }
            if (c == '(' || c == '{' || c == '[') {
                stacka.push(c);
            }
            if (stacka.isEmpty()) {
                return false;
            }
            if (c == ')') {
                char p = stacka.pop();
                if (p != '(') {
                    return false;
                }
            } else {
                if (c == '}') {
                    char p = stacka.pop();
                    if (p != '{') {
                        return false;
                    }
                } else {
                    if (c == ']') {
                        char p = stacka.pop();
                        if (p != '[') {
                            return false;
                        }
                    }
                }
            }
        }
        return stacka.isEmpty();
        // return false;
    }
}
