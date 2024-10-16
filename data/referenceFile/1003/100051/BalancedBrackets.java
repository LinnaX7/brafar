public class BalancedBrackets {

    static String pairFinder(String str) {
        if (str.charAt(0) == '{') {
            char firstOccurence = str.charAt(0);
            for (int m = 1; m < str.length(); m++) {
                if (str.charAt(m) == '}') {
                    char secondOccurence = str.charAt(m);
                    String temp1 = String.valueOf(str.charAt(0));
                    String temp2 = String.valueOf(str.charAt(m));
                    str = str.replace(temp1, "");
                    str = str.replace(temp2, "");
                    return str;
                }
            }
        }
        if (str.charAt(0) == '[') {
            char firstOccurence = str.charAt(0);
            for (int m = 1; m < str.length(); m++) {
                if (str.charAt(m) == ']') {
                    char secondOccurence = str.charAt(m);
                    String temp1 = String.valueOf(str.charAt(0));
                    String temp2 = String.valueOf(str.charAt(m));
                    str = str.replace(temp1, "");
                    str = str.replace(temp2, "");
                    return str;
                }
            }
        }
        if (str.charAt(0) == '(') {
            char firstOccurence = str.charAt(0);
            for (int m = 1; m < str.length(); m++) {
                if (str.charAt(m) == ')') {
                    char secondOccurence = str.charAt(m);
                    String temp1 = String.valueOf(str.charAt(0));
                    String temp2 = String.valueOf(str.charAt(m));
                    str = str.replace(temp1, "");
                    str = str.replace(temp2, "");
                    return str;
                }
            }
        }
        return "wrong";
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.\
        if (str == null && str.isEmpty() || str.length() % 2 == 1) {
            return false;
        }
        String strModify = str;
        for (int n = 0; n < str.length(); n++) {
            if (str.length() >= 4) {
                if (str.charAt(n) == '[') {
                    if (str.charAt(n + 1) == ')' || str.charAt(n + 1) == '}') {
                        return false;
                    }
                }
                if (str.charAt(n) == '(') {
                    if (str.charAt(n + 1) == ']' || str.charAt(n + 1) == '}') {
                        return false;
                    }
                }
                if (str.charAt(n) == '{') {
                    if (str.charAt(n + 1) == ')' || str.charAt(n + 1) == ']') {
                        return false;
                    }
                }
            }
        }
        if (strModify.length() == 2) {
            if (str.charAt(0) == '{') {
                if (str.charAt(1) == '{' || str.charAt(1) == '(' || str.charAt(1) == '[' || str.charAt(1) == ']' || str.charAt(1) == ')') {
                    return false;
                }
            }
            if (str.charAt(0) == '[') {
                if (str.charAt(1) == '{' || str.charAt(1) == '(' || str.charAt(1) == '[' || str.charAt(1) == ')' || str.charAt(1) == '}') {
                    return false;
                }
            }
            if (str.charAt(0) == '(') {
                if (str.charAt(1) == '{' || str.charAt(1) == '(' || str.charAt(1) == '[' || str.charAt(1) == '}' || str.charAt(1) == ']') {
                    return false;
                }
            }
            if (strModify.charAt(0) == '(') {
                if (strModify.charAt(1) == ')') {
                    return true;
                }
            }
            if (strModify.charAt(0) == '{') {
                if (strModify.charAt(1) == '}') {
                    return true;
                }
            }
            if (strModify.charAt(0) == '[') {
                if (strModify.charAt(1) == ']') {
                    return true;
                }
            }
        }
        if (strModify.length() >= 4) {
            while (strModify != null && !strModify.isEmpty()) {
                strModify = pairFinder(strModify);
                if (strModify == "wrong") {
                    return false;
                }
            }
            if (strModify.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
