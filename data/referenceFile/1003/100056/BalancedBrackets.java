import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // Identify whether str is null or empty
        if (str == null) {
            return false;
        }
        char[] array = str.toCharArray();
        if (array.length == 0) {
            return false;
        }
        // Use stack to identify balance
        Stack<Character> s = new Stack<Character>();
        for (int a = 0; a < array.length; a++) {
            if (array[a] == '(' || array[a] == '[' || array[a] == '{') {
                s.push(array[a]);
            } else {
                if (s.isEmpty()) {
                    return false;
                }
                if (array[a] == ')') {
                    if (s.pop() != '(') {
                        return false;
                    }
                } else {
                    if (array[a] == ']') {
                        if (s.pop() != '[') {
                            return false;
                        }
                    } else {
                        if (array[a] == '}') {
                            if (s.pop() != '{') {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        if (s.isEmpty()) {
            return true;
        }
        return false;
    }
}
