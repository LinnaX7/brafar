public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // to store the open brackets
        char[] data = new char[str.length()];
        if (// string is non-empty
        str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            // string contains only the six characters
            char member = str.charAt(i);
            if (member != '(' && member != ')' && member != '[' && member != ']' && member != '{' && member != '}') {
                return false;
            }
        }
        // point to the top of brackets stack
        int pointer = 0;
        // see if the first character is open bracket
        char startMember = str.charAt(0);
        if (startMember != '(' && startMember != '[' && startMember != '{') {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            if (character == '(' || character == '[' || character == '{') {
                data[pointer] = character;
                pointer += 1;
            }
            if (character == ')') {
                // case1
                if (data[pointer - 1] == '(') {
                    // equal to delete ()
                    pointer -= 1;
                } else {
                    return false;
                }
            }
            if (character == ']') {
                // case2
                if (data[pointer - 1] == '[') {
                    // equal to delete []
                    pointer -= 1;
                } else {
                    return false;
                }
            }
            if (character == '}') {
                // case3
                if (data[pointer - 1] == '{') {
                    // equal to delete {}
                    pointer -= 1;
                } else {
                    return false;
                }
            }
        }
        if (// finally see if the stack is empty
        pointer == 0) {
            return true;
        }
        return false;
    }
}
