import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // create the stack with string type
        Stack stackA = new Stack();
        // if the number of elements in stack is odd then it is impossible appear balance
        if (str.length() % 2 != 0) {
            return false;
        }
        while (!str.isEmpty()) {
            // get the first element of stack
            String character = str.substring(0, 1);
            // get the rest of elements
            str = str.substring(1);
            if (character.equals("{") || character.equals("[") || character.equals("(")) {
                // 如果是左括号，则压入栈
                stackA.push(character);
            } else {
                if (character.equals(")") || character.equals("]") || character.equals("}")) {
                    // check whether the stack is empty or not
                    if (stackA.isEmpty()) {
                        return false;
                    }
                    // check whether balance
                    if (character.equals(")")) {
                        if (stackA.peek().equals("(")) {
                            stackA.pop();
                            return true;
                        }
                        break;
                    } else {
                        if (character.equals("]")) {
                            if (stackA.peek().equals("[")) {
                                stackA.pop();
                                return true;
                            }
                            break;
                        } else {
                            if (character.equals("}")) {
                                if (stackA.peek().equals("{")) {
                                    stackA.pop();
                                    return true;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
