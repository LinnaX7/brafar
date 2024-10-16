public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.length() == 0) {
            return false;
        }
        char[] stack = new char[str.length()];
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{') {
                stack[p++] = str.charAt(i);
            } else {
                if (p > 0 && (str.charAt(i) == ')' || str.charAt(i) == ']' || str.charAt(i) == '}')) {
                    if (str.charAt(i) - stack[p - 1] <= 2) {
                        stack[--p] = '\0';
                    }
                } else {
                    return false;
                }
            }
        }
        return p == 0;
    }
    /*
    String s = "()[]{}"; 40 41 91 93 123 125
    for (int i=0; i<s.length(); i++) {
        System.out.println(s.charAt(i)+"  "+(int)s.charAt(i));
    }
     */
}
