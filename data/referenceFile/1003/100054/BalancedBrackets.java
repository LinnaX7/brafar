public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str == "") {
            return false;
        }
        Stack left = new Stack();
        char[] temp = str.toCharArray();
        for (char i : temp) {
            if (i != '{' && i != '}' && i != '[' && i != ']' && i != '(' && i != ')') {
                return false;
            }
            if (i == '(' || i == '[' || i == '{') {
                left.push(i);
            }
            if (i == ')' || i == ']' || i == '}') {
                char right = left.pop();
                if (right == '{') {
                    if (i == '}') {
                        continue;
                    } else {
                        return false;
                    }
                }
                if (right == '[') {
                    if (i == ']') {
                        continue;
                    } else {
                        return false;
                    }
                }
                if (right == '(') {
                    if (i == ')') {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        if (left.isEmpty()) {
            return true;
        }
        return false;
    }
}

class Stack {

    private int maxLength = 100;

    private int pointer = 0;

    private char[] stack = new char[maxLength];

    char pop() {
        if (pointer <= 0)
            return '*';
        return stack[--pointer];
    }

    void push(char c) {
        stack[pointer++] = c;
    }

    boolean isEmpty() {
        return pointer == 0;
    }
}
