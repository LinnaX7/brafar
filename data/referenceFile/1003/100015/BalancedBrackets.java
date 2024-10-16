public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int count = 0;
        char[] brackets = { '(', ')', '[', ']', '{', '}' };
        if (str.length() == 0) {
            return false;
        }
        if (str.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            count = 0;
            for (int y = 0; y < 6; y++) {
                if (str.charAt(i) != brackets[y]) {
                    count++;
                } else {
                    break;
                }
            }
            if (count == 6) {
                return false;
            }
        }
        char a = ' ';
        int i = 0;
        int posoftype = 0;
        int whichone = 0;
        int temp = str.length() - 1;
        String storingsametype = "";
        while (i < temp) {
            count = 0;
            storingsametype = "";
            for (int y = 0; y < 6; y++) {
                if (str.charAt(i) == brackets[y]) {
                    if (y % 2 == 1) {
                        return false;
                    }
                    posoftype = y;
                    break;
                }
            }
            for (int n = temp; n > i; n--) {
                if (str.charAt(n) == brackets[posoftype + 1]) {
                    storingsametype = storingsametype + String.valueOf(n);
                } else {
                    count++;
                }
            }
            if (count == temp - i) {
                return false;
            }
            for (int k = 0; k < storingsametype.length(); k++) {
                a = storingsametype.charAt(k);
                if (str.substring(i + 1, Integer.parseInt(String.valueOf(a))).length() % 2 == 0) {
                    whichone = a - '0';
                }
            }
            if (whichone == temp) {
                temp--;
            }
            if (whichone - i == 1) {
                i += 2;
            } else {
                i++;
            }
        }
        return true;
    }
}
