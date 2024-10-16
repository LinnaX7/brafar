public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // Check for rule 1
        if (str.length() == 0) {
            return false;
        }
        // Check for rule 2
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!isAllow(str.charAt(i))) {
                return false;
            }
        }
        // Check for rule 3
        return isOk(str);
    }

    private static boolean isAllow(char letter) {
        // Allow char
        char[] allow = { '(', ')', '{', '}', '[', ']' };
        for (char i : allow) {
            if (i == letter) {
                // Found
                return true;
            }
        }
        return false;
    }

    // Check is ok or not
    private static boolean isOk(String str) {
        // Allow pairs
        String[] pairs = { "()", "{}", "[]" };
        while (str.length() != 0) {
            boolean found = false;
            for (String pair : pairs) {
                int index = str.indexOf(pair);
                if (index != -1) {
                    // Pair found
                    // remove pair form str
                    str = remove(str, index);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // No pairs found
                return false;
            }
        }
        return true;
    }

    // Remove the found pair
    private static String remove(String str, int index) {
        return str.substring(0, index) + str.substring(index + 2);
    }
}
