public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // 1. is not empty
        if (str.isEmpty()) {
            return false;
        }
        // 2. contains only the six characters
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '[' && str.charAt(i) != ']' && str.charAt(i) != '{' && str.charAt(i) != '}') {
                return false;
            }
        }
        // 3. is balanced
        // set the capacity of stack to 100 by default
        char[] stack = new char[100];
        int pointer = 0;
        // first character is closing bracket
        if (str.charAt(0) == ')' || str.charAt(0) == ']' || str.charAt(0) == '}') {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                stack[pointer] = str.charAt(i);
                pointer++;
            } else {
                if (isMatch(stack[pointer - 1], str.charAt(i))) {
                    pointer--;
                } else {
                    return false;
                }
            }
        }
        // if all bracket are grouped, then it is balanced
        return pointer == 0;
    }

    public static boolean isMatch(char x, char y) {
        if (x == '(' && y == ')')
            return true;
        else if (x == '[' && y == ']')
            return true;
        else if (x == '{' && y == '}')
            return true;
        else
            return false;
    }
}
