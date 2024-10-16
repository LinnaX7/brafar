import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int len = str.length();
        Stack<Character> stack = new Stack<Character>();
        char pop = ' ';
        // checking if it's empty
        if (str.isEmpty()) {
            return false;
        }
        // checking for six characters
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '[' && str.charAt(i) != ']' && str.charAt(i) != '{' && str.charAt(i) != '}') {
                return false;
            }
        }
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                stack.push(str.charAt(i));
            } else {
                if (str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}') {
                    if (!stack.isEmpty()) {
                        pop = stack.pop();
                        if (pop == '(' && str.charAt(i) != ')') {
                            return false;
                        }
                        if (pop == '[' && str.charAt(i) != ']') {
                            return false;
                        }
                        if (pop == '{' && str.charAt(i) != '}') {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        // balanced brackets should pop all the stacks out
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}
