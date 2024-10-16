public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // System.out.println(str);
        boolean stringEmpty = false;
        boolean sixCharOnly = true;
        boolean isBalanced = false;
        if (str.length() == 0) {
            stringEmpty = true;
        }
        char[] stack = new char[str.length()];
        int stackTop = -1;
        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);
            // System.out.println(symbol);
            // Check if the symbol is (, ), [, ], { or }
            if (!(symbol == '(' || symbol == '[' || symbol == '{' || symbol == ')' || symbol == ']' || symbol == '}')) {
                sixCharOnly = false;
                break;
            }
            // Push if the symbol is (, [ and {
            if (symbol == '(' || symbol == '[' || symbol == '{') {
                stackTop++;
                stack[stackTop] = symbol;
            }
            // Check if the closing symbol matches the top of the stack
            if (symbol == ')' || symbol == ']' || symbol == '}') {
                if (symbol == ')') {
                    // remove ( symbol
                    if (stack[stackTop] == '(') {
                        stack[stackTop] = ' ';
                        stackTop--;
                    }
                }
                if (symbol == ']') {
                    // remove [ symbol
                    if (stack[stackTop] == '[') {
                        stack[stackTop] = ' ';
                        stackTop--;
                    }
                }
                if (symbol == '}') {
                    // remove { symbol
                    if (stack[stackTop] == '{') {
                        stack[stackTop] = ' ';
                        stackTop--;
                    }
                }
            }
        }
        // print stack
        /*
        System.out.println("RESULT: ");
        for (int i = 0; i < stack.length; i++) {
            System.out.printf("%c ", stack[i]);
        }

        System.out.printf("%d\n\n", stackTop);

         */
        if (stackTop == -1) {
            isBalanced = true;
        }
        return (!stringEmpty) && sixCharOnly && isBalanced;
    }
}
