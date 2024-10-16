public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str == null || str.isEmpty()) {
            return false;
        }
        int point = -1;
        char[] stack = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                point++;
                stack[point] = str.charAt(i);
            } else {
                if ((str.charAt(i) == ')' && stack[point] == '(') || (str.charAt(i) == '}' && stack[point] == '{') || (str.charAt(i) == ']' && stack[point] == '[')) {
                    point--;
                } else {
                    return false;
                }
            }
        }
        if (point == -1) {
            return true;
        }
        return false;
    }
}
