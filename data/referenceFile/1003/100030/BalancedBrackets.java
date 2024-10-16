public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        char[] a = new char[] { '(', ')', '[', ']', '{', '}' };
        int leng = str.length();
        if (leng == 0) {
            return false;
        }
        int[] numtran = new int[leng + 10];
        for (int i = 0; i < leng; i++) {
            for (int t = 0; t < 6; t++) {
                if (str.charAt(i) == a[t]) {
                    numtran[i] = t;
                    break;
                }
                if (t == 5) {
                    return false;
                }
            }
        }
        int[] mostack = new int[leng + 10];
        int to = 0;
        int sizet = 0;
        for (int i = 0; i < leng; i++) {
            if (numtran[i] % 2 == 0) {
                mostack[to++] = numtran[i];
                sizet++;
            } else {
                if (sizet == 0) {
                    return false;
                }
                if ((numtran[i] - 1) != mostack[to - 1]) {
                    return false;
                }
                sizet--;
                to--;
            }
        }
        if (sizet != 0) {
            return false;
        }
        return true;
    }
}
