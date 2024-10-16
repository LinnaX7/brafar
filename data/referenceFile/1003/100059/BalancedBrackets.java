public class BalancedBrackets {

    public static boolean brackets(String str) {
        int smallCount = 0, middleCount = 0, bigCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if (temp == '(') {
                smallCount++;
            }
            if (temp == ')') {
                smallCount--;
            }
            if (temp == '[') {
                middleCount++;
            }
            if (temp == ']') {
                middleCount--;
            }
            if (temp == '{') {
                bigCount++;
            }
            if (temp == '}') {
                bigCount--;
            }
            if (smallCount < 0 || middleCount < 0 || bigCount < 0) {
                return false;
            }
        }
        if (smallCount == 0 && middleCount == 0 && bigCount == 0) {
            return true;
        }
        return false;
    }

    public static boolean testPair(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char temp = str.charAt(i);
            if ((temp == '(') || (temp == '[') || (temp == '{')) {
                sb.append(temp);
            } else {
                int lengthArray = sb.length();
                if (temp == ')') {
                    if (sb.charAt(lengthArray - 1) == '(') {
                        sb.deleteCharAt(lengthArray - 1);
                    } else {
                        return false;
                    }
                }
                if (temp == '}') {
                    if (sb.charAt(lengthArray - 1) == '{') {
                        sb.deleteCharAt(lengthArray - 1);
                    } else {
                        return false;
                    }
                }
                if (temp == ']') {
                    if (sb.charAt(lengthArray - 1) == '[') {
                        sb.deleteCharAt(lengthArray - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        if (sb.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        boolean sixChar = false;
        if (!str.equals("")) {
            sixChar = true;
            for (int i = 0; i < str.length(); i++) {
                char temp = str.charAt(i);
                if ((temp != '(') && (temp != ')') && (temp != '[') && (temp != ']') && (temp != '{') && (temp != '}')) {
                    sixChar = false;
                }
            }
        }
        if ((BalancedBrackets.brackets(str) && sixChar)) {
            if (BalancedBrackets.testPair(str)) {
                return true;
            }
        }
        return false;
    }
}
