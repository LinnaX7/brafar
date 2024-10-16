import java.util.Stack;

public class BalancedBrackets {

    public static boolean matchClosing(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.length() == 0) {
            return false;
        }
        Stack<Character> stack = new Stack<Character>();
        for (char curChar : str.toCharArray()) {
            if (curChar == '(' || curChar == '[' || curChar == '{') {
                // opening brackets, push into the stack
                stack.push(curChar);
                continue;
            }
            // a matching opening bracket does not exist while a closing bracket does
            // or other characters appear
            if (stack.size() > 0 && matchClosing(stack.peek(), curChar)) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
