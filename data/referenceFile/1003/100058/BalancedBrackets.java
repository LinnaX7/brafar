import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // if empty or odd # of char
        if (str.isEmpty() || (str.length() % 2 == 1)) {
            return false;
        }
        Stack<Integer> box = new Stack<>();
        char[] iterChar = str.toCharArray();
        for (char item : iterChar) {
            if (item == '(') {
                box.push(1);
            } else {
                if (item == '{') {
                    box.push(2);
                } else {
                    if (item == '[') {
                        box.push(3);
                    } else {
                        if (item == ')') {
                            if (box.pop() != 1) {
                                return false;
                            }
                        } else {
                            if (item == '}') {
                                if (box.pop() != 2) {
                                    return false;
                                }
                            } else {
                                if (item == ']') {
                                    if (box.pop() != 3) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!box.isEmpty()) {
            return false;
        }
        return true;
    }
}
