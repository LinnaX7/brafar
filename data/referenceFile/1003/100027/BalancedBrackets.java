public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                count1++;
                counter++;
            }
            if (str.charAt(i) == ')') {
                count1--;
                counter++;
            }
            if (str.charAt(i) == '[') {
                count2++;
                counter++;
            }
            if (str.charAt(i) == ']') {
                count2--;
                counter++;
            }
            if (str.charAt(i) == '{') {
                count3++;
                counter++;
            }
            if (str.charAt(i) == '}') {
                count3--;
                counter++;
            }
        }
        if (counter != str.length()) {
            return false;
        }
        if (str.length() == 0) {
            return false;
        }
        if (str.charAt(0) == ')') {
            return false;
        }
        if (str.charAt(0) == ']') {
            return false;
        }
        if (str.charAt(0) == '}') {
            return false;
        }
        if (count1 != 0) {
            return false;
        }
        if (count2 != 0) {
            return false;
        }
        if (count3 != 0) {
            return false;
        }
        for (int g = 0; g < str.length(); g++) {
            if (str.charAt(g) == '(') {
                if (str.charAt(g + 1) == ']') {
                    return false;
                }
                if (str.charAt(g + 1) == '}') {
                    return false;
                }
            }
            if (str.charAt(g) == '[') {
                if (str.charAt(g + 1) == '}') {
                    return false;
                }
                if (str.charAt(g + 1) == ')') {
                    return false;
                }
            }
            if (str.charAt(g) == '{') {
                if (str.charAt(g + 1) == ')') {
                    return false;
                }
                if (str.charAt(g + 1) == ']') {
                    return false;
                }
            }
        }
        return true;
    }
}
// Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
// the six characters, and 3) is balanced.
