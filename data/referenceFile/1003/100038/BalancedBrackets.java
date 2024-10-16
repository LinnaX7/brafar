import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        boolean N_emp;
        boolean sixchar = true;
        boolean bal = false;
        // use a stack to push and pop bracket, which can check the string is balanced or not
        Stack<Character> stack = new Stack<>();
        N_emp = !str.isEmpty();
        for (char bracket : str.toCharArray()) {
            if (bracket == '(' || bracket == '[' || bracket == '{' || bracket == ')' || bracket == ']' || bracket == '}') {
                if (bracket == '(' || bracket == '[' || bracket == '{') {
                    stack.push(bracket);
                    bal = false;
                } else {
                    if (bracket == ')') {
                        if (// pop out the top of stack to match the balanced bracket
                        stack.pop() == '(') {
                            bal = true;
                        } else {
                            bal = false;
                        }
                    } else {
                        if (bracket == ']') {
                            if (stack.pop() == '[') {
                                bal = true;
                            } else {
                                bal = false;
                            }
                        } else {
                            if (bracket == '}') {
                                if (stack.pop() == '{') {
                                    bal = true;
                                } else {
                                    bal = false;
                                }
                            }
                        }
                    }
                }
            } else {
                sixchar = false;
            }
        }
        if (// fulfill 3 requirements
        N_emp && sixchar && bal) {
            return true;
        }
        return false;
    }
}
