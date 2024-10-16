public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int l = str.length();
        boolean result = true;
        if (l == 0) {
            result = false;
        } else {
            for (int i = 0; i < l; i++) {
                if ((str.charAt(i) == '{') || (str.charAt(i) == '}') || (str.charAt(i) == '[') || (str.charAt(i) == ']') || (str.charAt(i) == '(') || (str.charAt(i) == ')')) {
                    continue;
                } else {
                    result = false;
                }
            }
        }
        if (result) {
            boolean change = true;
            while (change) {
                change = false;
                int length;
                length = str.length();
                if (length % 2 != 0) {
                    result = false;
                    break;
                }
                if (length == 0) {
                    break;
                } else {
                    for (int i = 0; i < length - 1; i++) {
                        if ((str.charAt(i) == '{' && str.charAt(i + 1) == '}') || (str.charAt(i) == '[' && str.charAt(i + 1) == ']') || (str.charAt(i) == '(' && str.charAt(i + 1) == ')')) {
                            change = true;
                            if (i + 2 < length) {
                                if (i == 0) {
                                    str = str.substring(i + 2, length);
                                } else {
                                    str = str.substring(0, i) + str.substring(i + 2, length);
                                }
                            } else {
                                str = str.substring(0, i);
                            }
                            break;
                        }
                        if (i == length - 2) {
                            if (!change) {
                                result = false;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
