import java.util.*;

public class BalancedBrackets {

    public static char[][] perfectbalance = { { '{', '}' }, { '[', ']' }, { '(', ')' } };

    public static boolean isnotBracket(String a) {
        char[] bracket = { '{', '}', '[', ']', '(', ')' };
        char[] array = a.toCharArray();
        int n = 0;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < 6; j++) {
                if (array[i] == bracket[j]) {
                    n = n + 1;
                }
            }
        }
        if (n == a.length()) {
            return false;
        }
        return true;
    }

    public static boolean isOpen(char a) {
        for (char[] couple : perfectbalance) {
            if (couple[0] == a) {
                return true;
            }
        }
        return false;
    }

    public static boolean partner(char x, char y) {
        for (char[] couple : perfectbalance) {
            if (couple[0] == x) {
                return couple[1] == y;
            }
        }
        return false;
    }

    public static boolean isBalanced(String str) {
        if (str.isEmpty()) {
            return false;
        }
        if (isnotBracket(str)) {
            return false;
        }
        Stack<Character> brancket = new Stack<Character>();
        for (char br : str.toCharArray()) {
            if (isOpen(br)) {
                brancket.push(br);
            } else {
                if (brancket.isEmpty()) {
                    return false;
                }
                if (!partner(brancket.pop(), br)) {
                    return false;
                }
            }
        }
        return brancket.isEmpty();
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
    }
}
