import java.util.ArrayList;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // zero-sized input
        if (str.length() == 0) {
            return false;
        }
        // stack
        ArrayList<Character> stack = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char counter = str.charAt(i);
            if (linearSearch(true, counter)) {
                stack.add(str.charAt(i));
            } else {
                if (linearSearch(false, counter)) {
                    if (stack.size() != 0) {
                        char peek = stack.get(stack.size() - 1);
                        if (peek == '(' && counter == ')') {
                            stack.remove(stack.size() - 1);
                        } else {
                            if (peek == '[' && counter == ']') {
                                stack.remove(stack.size() - 1);
                            } else {
                                if (peek == '{' && counter == '}') {
                                    stack.remove(stack.size() - 1);
                                }
                            }
                        }
                    } else {
                        // no open brackets in the stack
                        return false;
                    }
                } else {
                    // invalid character
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public static boolean linearSearch(boolean openClose, char counter) {
        boolean flag = false;
        char[] open_brackets = { '(', '[', '{' };
        char[] close_brackets = { ')', ']', '}' };
        if (openClose)
            for (int i = 0; i < 3; i++) {
                if (counter == open_brackets[i]) {
                    flag = true;
                    break;
                }
            }
        else
            for (int i = 0; i < 3; i++) {
                if (counter == close_brackets[i]) {
                    flag = true;
                    break;
                }
            }
        return flag;
    }
}
