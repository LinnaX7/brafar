import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        boolean resultBalanced;
        Stack<Character> st = new Stack<>();
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
            resultBalanced = true;
        } else {
            resultBalanced = false;
        }
        if ((str.trim()).length() != 0 && isOkCharacters(str) && resultBalanced) {
            return true;
        }
        return false;
    }

    public static boolean isOkCharacters(String str) {
        String stringWithoutSpace = str.trim();
        for (int i = 0; i < stringWithoutSpace.length(); i++) {
            if (stringWithoutSpace.charAt(i) != '[' && stringWithoutSpace.charAt(i) != ']' && stringWithoutSpace.charAt(i) != '(' && stringWithoutSpace.charAt(i) != ')' && stringWithoutSpace.charAt(i) != '{' && stringWithoutSpace.charAt(i) != '}') {
                return false;
            }
        }
        return true;
    }
}
