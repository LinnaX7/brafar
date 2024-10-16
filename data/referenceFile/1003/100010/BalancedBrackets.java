public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        int lengthy = str.length();
        // store index
        boolean[] arr = new boolean[lengthy];
        if (!str.isEmpty() && (lengthy % 2 == 0)) {
            for (int a = 0; a < lengthy; a++) {
                // check whether only contain specific character
                int count = 0;
                if ((str.charAt(a) != '(') && (str.charAt(a) != ')')) {
                    count++;
                }
                if ((str.charAt(a) != '[') && (str.charAt(a) != ']')) {
                    count++;
                }
                if ((str.charAt(a) != '{') && (str.charAt(a) != '}')) {
                    count++;
                }
                if (count == 3) {
                    return false;
                }
            }
            for (int i = 0; i < lengthy; i++) {
                char temp = str.charAt(i);
                for (int j = lengthy - 1; j > i; j--) {
                    if (str.charAt(j) == temp + 1 || str.charAt(j) == temp + 2) {
                        boolean flag = true;
                        if (arr[i] || arr[j]) {
                            flag = false;
                        }
                        int x = j - 1, y = j - 1;
                        for (int k = j - 1; k > i; k--) {
                            if (str.charAt(k) == temp) {
                                x = k;
                            }
                            if (str.charAt(k) == temp + 1 || str.charAt(k) == temp + 2) {
                                y = k;
                            }
                        }
                        if (x > y) {
                            flag = false;
                        }
                        if (flag && (j - i) % 2 != 0) {
                            arr[i] = true;
                            arr[j] = true;
                            break;
                        }
                    }
                }
            }
            for (boolean b : arr) {
                if (!b) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
