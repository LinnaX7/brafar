import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        Stack<Character> charStack = new Stack<>();
        if (str.length() != 0) {
            if (str.length() % 2 == 0) {
                for (int i = 0; i < str.length(); i++) {
                    char c = str.charAt(i);
                    if (c == '(' || c == '{' || c == '[') {
                        charStack.push(c);
                    }
                    if (c == ')' && charStack.peek() == '(') {
                        charStack.pop();
                    }
                    if (c == '}' && charStack.peek() == '{') {
                        charStack.pop();
                    }
                    if (c == ']' && charStack.peek() == '[') {
                        charStack.pop();
                    }
                }
                boolean result;
                result = charStack.empty();
                return result;
            }
        }
        return false;
    }
}
