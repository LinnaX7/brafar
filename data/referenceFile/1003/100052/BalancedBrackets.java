import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        String allowedCharacters = "(){}[]";
        // Java Default implementation of stack
        Stack<Character> stack = new Stack<>();
        if (str == null || str.isEmpty() || str.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (!allowedCharacters.contains(Character.toString(temp))) {
                return false;
            }
            if (temp == '(' || temp == '{' || temp == '[') {
                stack.push(temp);
            } else {
                if (!stack.isEmpty()) {
                    char openingBracket = stack.pop();
                    if (temp == '(' && openingBracket != ')') {
                        return false;
                    }
                    if (temp == '}' && openingBracket != '{') {
                        return false;
                    }
                    if (temp == '[' && openingBracket != '[') {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        // check if the stack still contain opening brackets after the loop(for cases that only have opening brackets)
        // cases that have unbalanced are considered in the above loop
        if (!stack.isEmpty()) {
            return false;
        }
        // return true if the string could pass all of the above condition
        return true;
    }
}
