import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str != null) {
            if (str.contains("(") || str.contains(")") || str.contains("{") || str.contains("}") || str.contains("[") || str.contains("]")) {
                Stack<Character> sta = new Stack<Character>();
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') {
                        sta.push(str.charAt(i));
                    } else {
                        if (!sta.empty()) {
                            if ((str.charAt(i) == ')' && sta.peek() == '(') || (str.charAt(i) == '}' && sta.peek() == '{') || (str.charAt(i) == ']' && sta.peek() == '[')) {
                                sta.pop();
                            } else {
                                sta.push(str.charAt(i));
                            }
                        }
                    }
                }
                if (sta.empty()) {
                    return true;
                }
            }
        }
        return false;
    }
}
