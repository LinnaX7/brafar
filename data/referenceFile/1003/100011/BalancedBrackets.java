import java.util.HashMap;
import java.util.ArrayDeque;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int len = str.length();
        if (len == 0) {
            return false;
        }
        HashMap<Character, Character> pair = new HashMap<Character, Character>() {

            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };
        ArrayDeque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (pair.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pair.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                if (!pair.containsValue(ch)) {
                    // illegal character
                    return false;
                }
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
