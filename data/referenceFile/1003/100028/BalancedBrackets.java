import java.util.Scanner;
import java.util.*;

public class BalancedBrackets {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        isBalanced(str);
    }

    public static boolean isBalanced(String str) {
        char[] s = str.toCharArray();
        Stack stack = new Stack();
        if (s.length == 0) {
            return false;
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i] != '(' && s[i] != ')' && s[i] != '[' && s[i] != ']' && s[i] != '{' && s[i] != '}') {
                return false;
            }
        }
        for (int i = 0; i < s.length; i++) {
            // System.out.println(stack);
            if (s[i] == '(' || s[i] == '[' || s[i] == '{') {
                stack.push(s[i]);
            }
            if (s[i] == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                char c = (char) stack.pop();
                if (c != '(') {
                    return false;
                }
            }
            if (s[i] == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                char c = (char) stack.pop();
                if (c != '[') {
                    return false;
                }
            }
            if (s[i] == '}') {
                if (stack.isEmpty()) {
                    return false;
                }
                char c = (char) stack.pop();
                if (c != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
