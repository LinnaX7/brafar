public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        String[] BRACKET_LIST = { "()", "{}", "[]" };
        boolean flag;
        if (str.isEmpty()) {
            return false;
        }
        do {
            flag = false;
            for (int i = 0; i < 3; i++) {
                if (str.contains(BRACKET_LIST[i])) {
                    str = str.replace(BRACKET_LIST[i], "");
                    flag = true;
                }
            }
        } while (flag);
        return str.isEmpty();
    }
}
