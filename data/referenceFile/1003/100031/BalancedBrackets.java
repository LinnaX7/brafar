public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int len = str.length();
        // 1) non-empty
        if (!(len > 0)) {
            return false;
        }
        // check if in pairs
        if (len % 2 != 0) {
            return false;
        }
        // 2) contains contains only the six characters
        for (int i = 0; i < len; i++) {
            if ((str.charAt(i) != '(') && (str.charAt(i) != ')') && (str.charAt(i) != '[') && (str.charAt(i) != ']') && (str.charAt(i) != '{') && (str.charAt(i) != '}')) {
                return false;
            }
        }
        /* create a new string where left brackets are kept and the right bracket are
        transferred into the respective left brackets

        Thus, checking if right brackets pair up with left brackets are transformed into check whether
        the items in temp and str are the same
        */
        char[] temp = new char[len];
        for (int i = 0; i < len; i++) {
            if ((str.charAt(i) == '(') || (str.charAt(i) == '{') || (str.charAt(i) == '[')) {
                temp[i] = str.charAt(i);
            } else {
                if (str.charAt(i) == ')') {
                    temp[i] = '(';
                } else {
                    if (str.charAt(i) == ']') {
                        temp[i] = '[';
                    } else {
                        if (str.charAt(i) == '}') {
                            temp[i] = '{';
                        }
                    }
                }
            }
        }
        // create left array to store left brackets and right repectively
        char[] left = new char[len];
        char[] right = new char[len];
        int indexLeft = 0;
        int indexRight = 0;
        for (int i = 0; i < len; i++) {
            if ((str.charAt(i) == '{') || (str.charAt(i) == '[') || (str.charAt(i) == '(')) {
                left[indexLeft] = str.charAt(i);
                indexLeft++;
            } else {
                indexRight++;
                // line 70
                if (indexLeft - 1 >= 0) {
                    if (temp[i] != left[indexLeft - 1]) {
                        return false;
                    }
                    indexLeft--;
                } else {
                    return false;
                }
            }
        }
        /*check whether there are both left and right arrays
        no need to check indexLeft==0, because the code in line 70
        already permits there are left brackets
         */
        if (indexRight == 0) {
            return false;
        }
        return true;
    }
}
