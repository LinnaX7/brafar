import java.util.*;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        Stack<Character> stk = new Stack<Character>();
        if (str == "") {
            return false;
        }
        for (char cha : str.toCharArray()) {
            if (cha == '(' || cha == '[' || cha == '{') {
                stk.push(cha);
            } else {
                if (cha == ')' && !stk.isEmpty() && stk.peek() == '(') {
                    stk.pop();
                } else {
                    if (cha == ']' && !stk.isEmpty() && stk.peek() == '[') {
                        stk.pop();
                    } else {
                        if (cha == '}' && !stk.isEmpty() && stk.peek() == '{') {
                            stk.pop();
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return stk.isEmpty();
    }
}
