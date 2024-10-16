public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        String theString = str;
        int stringLength = theString.length();
        StringBuffer storeLeft = new StringBuffer("");
        String leftBracket = "";
        // int the length of the left bracket
        int stackLength = 0;
        // first it is not empty
        if (stringLength == 0) {
            return false;
        }
        // second it only contain six bracket characters
        for (int i = 0; i < stringLength; i++) {
            if (theString.charAt(i) != '(' && theString.charAt(i) != '{' && theString.charAt(i) != '[' && theString.charAt(i) != ')' && theString.charAt(i) != '}' && theString.charAt(i) != ']') {
                return false;
            }
        }
        // Put the left bracket in the stack
        for (int i = 0; i < stringLength; i++) {
            if (theString.charAt(i) == '(' || theString.charAt(i) == '{' || theString.charAt(i) == '[') {
                storeLeft.append(theString.charAt(i));
            }
            // calculate the number of the data in the stack
            stackLength = storeLeft.length();
            // find out the right bracket
            if (theString.charAt(i) == ')' || theString.charAt(i) == '}' || theString.charAt(i) == ']') {
                if (stackLength == 0) {
                    return false;
                }
                int tempLeft = storeLeft.charAt(stackLength - 1);
                int tempRight = theString.charAt(i);
                // If the right bracket is able to match the left bracket pop out of the stack
                if (Math.abs(tempLeft - tempRight) <= 2) {
                    storeLeft.deleteCharAt(stackLength - 1);
                    stackLength -= 1;
                } else {
                    return false;
                }
            }
        }
        // check the whether the stack is empty
        if (stackLength != 0) {
            return false;
        }
        return true;
    }
}
