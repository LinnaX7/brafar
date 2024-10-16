import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (!str.isEmpty() && validStr(str)) {
            // Execute the checking process when 'str' is non-empty and only contains those six characters. Otherwise, return false.
            // initial an empty stack for checking process
            Stack check = new Stack();
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) == '(') || (str.charAt(i) == '{') || (str.charAt(i) == '[')) {
                    // get char by index from str
                    // If the current char is a open bracket, push it to the top of stack
                    check.push(str.charAt(i));
                } else {
                    if ((str.charAt(i) == ')') || (str.charAt(i) == '}') || (str.charAt(i) == ']')) {
                        // if the char is a close bracket, try to pop the open bracket from the top of stack.
                        if (!check.empty()) {
                            // if stack is empty, it means that there is no open bracket the match the last close bracket.
                            // For stack empty cases, go to the "else" part
                            // pop the char from the top of stack
                            char popItem = (char) check.pop();
                            if (!((popItem == '(' && str.charAt(i) == ')') || (popItem == '{' && str.charAt(i) == '}') || (popItem == '[' && str.charAt(i) == ']'))) {
                                // if the bracket pair do not match, return false
                                return false;
                            }
                        } else {
                            // If stack is empty when the program try to pop the character,
                            return false;
                        }
                        // there must be an extra close bracket at last part of str, so it is not balanced
                    }
                }
            }
            // For those cases that come to this line, the first or last n-1 characters must be balanced
            if (!check.empty()) {
                // Finally, check whether the stack is Empty.
                // if it is not empty, the cases should be like "([]))" or "((())", which is balanced for first or last n-1 characters.
                return false;
                // But those cases are not balanced, so return false.
            }
            // if it is empty, the str do not have the problem states above, so return true.
            return true;
        }
        return false;
    }

    public static boolean validStr(String str) {
        // Check whether the string contains the 6 characters only
        for (int i = 0; i < str.length(); i++) {
            // Check every character in str, return false if anyone is not valid
            if (!((str.charAt(i) == '(') || (str.charAt(i) == '{') || (str.charAt(i) == '[') || (str.charAt(i) == ')') || (str.charAt(i) == '}') || (str.charAt(i) == ']'))) {
                // If the character that out of this range is found, return false.
                return false;
            }
        }
        // After finishing the loop without return false value, the str is valid.
        return true;
    }
}
