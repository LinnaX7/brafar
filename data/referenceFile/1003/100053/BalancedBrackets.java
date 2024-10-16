import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        String str = "(({}))";
        System.out.println(isBalanced(str));
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        boolean boolBalanced = false;
        // Stack containing brackets
        Stack<Character> characterStack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '{' || str.charAt(i) == '(' || str.charAt(i) == '[') {
                // if the character is an opening brackets, we put it in the stack
                characterStack.push(str.charAt(i));
            } else {
                if (!characterStack.empty() && ((str.charAt(i) == '}' && characterStack.peek() == '{') || (str.charAt(i) == ')' && characterStack.peek() == '(') || (str.charAt(i) == ']' && characterStack.peek() == '['))) {
                    // if the character is closing the stack's above bracket, we don't put it on the stack and we remove the stack's above character
                    characterStack.pop();
                } else {
                    // else we put it in the stack
                    characterStack.push(str.charAt(i));
                }
            }
        }
        if (// if, at the end, the stack is empty all the brackets were balanced
        characterStack.empty()) {
            // if, at the end, the stack is empty all the brackets were balanced
            boolBalanced = true;
        } else {
            boolBalanced = false;
        }
        if (isNonEmpty(str) && isSixCharacters(str) && boolBalanced) {
            return true;
        }
        return false;
    }

    public static boolean isNonEmpty(String str) {
        if ((str.trim()).length() != 0)
            return true;
        else
            return false;
    }

    public static boolean isSixCharacters(String str) {
        boolean result = true;
        String trimStr = str.trim();
        for (int i = 0; i < trimStr.length(); i++) {
            if (trimStr.charAt(i) != '[' && trimStr.charAt(i) != ']' && trimStr.charAt(i) != '(' && trimStr.charAt(i) != ')' && trimStr.charAt(i) != '{' && trimStr.charAt(i) != '}') {
                result = false;
                break;
            }
        }
        return result;
    }
}
