public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty()) {
            return false;
        }
        int[] arr = new int[100000];
        int Ridx = 0;
        int Lidx = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '(') {
                arr[Ridx++] = 1;
                ++Lidx;
            } else {
                if (str.charAt(i) == '[') {
                    arr[Ridx++] = 2;
                    ++Lidx;
                } else {
                    if (str.charAt(i) == '{') {
                        arr[Ridx++] = 3;
                        ++Lidx;
                    } else {
                        if (str.charAt(i) == ')') {
                            if (arr[Lidx - 1] != 1 || Lidx < 1) {
                                return false;
                            }
                            --Lidx;
                            --Ridx;
                        } else {
                            if (str.charAt(i) == ']') {
                                if (arr[Lidx - 1] != 2 || Lidx < 1) {
                                    return false;
                                }
                                --Lidx;
                                --Ridx;
                            } else {
                                if (str.charAt(i) == '}') {
                                    if (arr[Lidx - 1] != 3 || Lidx < 1) {
                                        return false;
                                    }
                                    --Lidx;
                                    --Ridx;
                                } else {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (Lidx == 0) {
            return true;
        }
        return false;
    }
}
