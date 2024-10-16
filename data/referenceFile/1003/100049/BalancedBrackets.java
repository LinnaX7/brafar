import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // create a new stack to store the string contemporary
        Stack<Character> stack = new Stack<>();
        boolean result;
        if (str.isEmpty()) {
            // if the str is empty, then it will return false directly to satisfy the condition 1
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            // for balance to satisfy the condition 3
            // start from the beginning of the str
            char c = str.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                // only input the prefix and if detect suffix then pop the prefix for as a pair
                stack.push(c);
            } else {
                if (c == ')' && stack.peek() == '(') {
                    // if occur suffix, detect whether the prefix is before input(on the peek of the stack)
                    stack.pop();
                } else {
                    if (c == ']' && stack.peek() == '[') {
                        // ,or they can't be as a pair
                        stack.pop();
                    } else {
                        if (c == '}' && stack.peek() == '{') {
                            // only can be as a pair could pop the prefix
                            stack.pop();
                        } else {
                            // if there are any str differ from this 6 string, it will return false directly to satisfy the condition 2
                            return false;
                        }
                    }
                }
            }
        }
        if (stack.empty()) {
            // if the stack is empty, means that all the string be as a pair, or it will remain in the stack
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        isBalanced("NULL");
    }
}
// OOP Assignment 1, Question 3
// CHEN Derun, Shanpoo
// 21098424d
