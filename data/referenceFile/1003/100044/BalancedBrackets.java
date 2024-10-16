import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // Check if it's non-empty
        if (str.length() == 0) {
            return false;
        }
        // Check if it contains only the six characters
        for (int i = 0; i < str.length(); i++) {
            if ((str.charAt(i) != '(') && (str.charAt(i) != ')') && (str.charAt(i) != '[') && (str.charAt(i) != ']') && (str.charAt(i) != '{') && (str.charAt(i) != '}')) {
                return false;
            }
        }
        // Checking balanced
        Stack<Character> stack = new Stack<Character>();
        boolean isBalanced = true;
        for (int i = 0; i < str.length(); i++) {
            // Scan every char in the string. If the brackets is a open brackets, append it into the char stack.
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                stack.push(str.charAt((i)));
            } else {
                /*If the scanned char is a closing bracket, if the top stack element is a open bracket that matches with it's
            respective opening bracket, pop(remove) the top element of the stack.
            If the scanned char is a closing bracket that DOES NOT MATCH it's respective opening bracket, continue to append
            that char into the array.
            * */
                if (str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}') {
                    if (stack.peek() == '(' && str.charAt(i) == ')') {
                        stack.pop();
                    } else {
                        if (stack.peek() == '[' && str.charAt(i) == ']') {
                            stack.pop();
                        } else {
                            if (stack.peek() == '{' && str.charAt(i) == '}') {
                                stack.pop();
                            } else {
                                stack.push(str.charAt(i));
                            }
                        }
                    }
                }
            }
        }
        // If all brackets matches with each other, at the end every opening bracket elements should be removed and stack is empty.
        // If that is the case, return true, other wise, flag the test as failed and return false.
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }
}
