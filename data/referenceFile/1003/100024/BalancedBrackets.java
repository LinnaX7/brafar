public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty()) {
            return false;
        }
        int l = str.length();
        int[] mark = new int[l];
        if (str.charAt(l - 1) == '(' || str.charAt(l - 1) == '[' || str.charAt(l - 1) == '{') {
            return false;
        }
        for (int i = 0; i < l; ) {
            if (mark[i] == 1) {
                i++;
            }
            if (mark[i] == 0 && (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{')) {
                int x = i;
                while (mark[x + 1] != 0) {
                    x++;
                    if (x + 1 >= l) {
                        return false;
                    }
                }
                x++;
                if ((str.charAt(i) == '(' && str.charAt(x) == ')') || (str.charAt(i) == '[' && str.charAt(x) == ']') || (str.charAt(i) == '{' && str.charAt(x) == '}')) {
                    mark[x] = 1;
                    mark[i] = 1;
                    i = 0;
                    continue;
                }
            }
            i++;
        }
        for (int a : mark) {
            if (a == 0) {
                return false;
            }
        }
        return true;
    }
}
