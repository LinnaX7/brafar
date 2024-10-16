import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        Stack<Character> st = new Stack<>();
        if (str.isEmpty()) {
            return false;
        }
        if (str.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '{' || str.charAt(i) == '(' || str.charAt(i) == '[') {
                st.push(str.charAt(i));
            } else {
                if (!st.empty() && ((str.charAt(i) == '}' && st.peek() == '{') || (str.charAt(i) == ')' && st.peek() == '(') || (str.charAt(i) == ']' && st.peek() == '['))) {
                    st.pop();
                } else {
                    st.push(str.charAt(i));
                }
            }
        }
        if (st.empty()) {
            return true;
        }
        return false;
    }
}
