import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        Stack<Character> sta = new Stack();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '{' || str.charAt(i) == '[' || str.charAt(i) == '(') {
                sta.push(str.charAt(i));
            } else {
                if (!sta.empty() && ((str.charAt(i) == ']' && sta.peek() == '[') || (str.charAt(i) == '}' && sta.peek() == '{') || (str.charAt(i) == ')' && sta.peek() == '('))) {
                    sta.pop();
                } else {
                    sta.push(str.charAt(i));
                    return false;
                }
            }
        }
        if (str.isEmpty()) {
            return false;
        }
        if (sta.empty()) {
            return true;
        }
        return false;
    }
}
