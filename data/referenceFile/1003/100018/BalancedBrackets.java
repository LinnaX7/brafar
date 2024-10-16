public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int len = str.length();
        if (len <= 0) {
            return false;
        }
        char[] stack = new char[len + 1];
        int top = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack[top++] = c;
            } else {
                if (c == ')') {
                    if (!(top > 0 && stack[top - 1] == '(')) {
                        return false;
                    }
                } else {
                    if (c == ']') {
                        if (!(top > 0 && stack[top - 1] == '[')) {
                            return false;
                        }
                    } else {
                        if (c == '}') {
                            if (!(top > 0 && stack[top - 1] == '{')) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
                top--;
            }
        }
        if (top == 0) {
            return true;
        }
        return false;
    }
}
