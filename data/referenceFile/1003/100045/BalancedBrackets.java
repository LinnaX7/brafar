public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        StringBuilder open = new StringBuilder("");
        int len = str.length();
        // if the length od str is odd, it cannot be balanced
        if (len % 2 != 0) {
            return false;
        }
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                    open.append(str.charAt(i));
                }
                int length1 = open.length();
                // judge whether it can be balanced
                // judgement for ( )
                if (str.charAt(i) == ')') {
                    if (open.charAt(length1 - 1) != '(') {
                        return false;
                    }
                    open.deleteCharAt(length1 - 1);
                    length1 = length1 - 1;
                }
                // judgement for [ ]
                if (str.charAt(i) == ']') {
                    if (open.charAt(length1 - 1) != '[') {
                        return false;
                    }
                    open.deleteCharAt(length1 - 1);
                    length1 = length1 - 1;
                }
                // judgement for { }
                if (str.charAt(i) == '}') {
                    if (open.charAt(length1 - 1) != '{') {
                        return false;
                    }
                    open.deleteCharAt(length1 - 1);
                    length1 = length1 - 1;
                }
                // if there is nothing in open brackets,it is true
                if (length1 == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
