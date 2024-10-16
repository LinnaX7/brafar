public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // 1) Is non-empty
        if (str.length() == 0) {
            return false;
        }
        // 2) Contains only the foregoing characters
        // 3) Is balanced
        char[] stackyStack = new char[str.length()];
        int pointerStack = 0;
        for (int index = 0; index < str.length(); index++) {
            // If it loops to "open" bracket, push the item into the stack
            if (str.charAt(index) == '(' || str.charAt(index) == '[' || str.charAt(index) == '{') {
                stackyStack[pointerStack] = str.charAt(index);
                pointerStack++;
            } else // If it loops to "close" bracket and the bracket is of same type, pop the stack
            {
                if ((str.charAt(index) == ')' && stackyStack[pointerStack - 1] == '(') || (str.charAt(index) == ']' && stackyStack[pointerStack - 1] == '[') || (str.charAt(index) == '}' && stackyStack[pointerStack - 1] == '{')) {
                    stackyStack[pointerStack - 1] = 0;
                    pointerStack--;
                } else // If string contains illegal characters, return false
                {
                    return false;
                }
            }
        }
        // If pointerStack == 0, it means the string is balanced with only the six characters
        return pointerStack == 0;
    }
}
