public class BalancedBrackets {

    public static boolean isBalanced(String s) {
        if (s.isEmpty()) {
            return false;
        }
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '[' && s.charAt(i) != ']' && s.charAt(i) != '(' && s.charAt(i) != ')' && s.charAt(i) != '{' && s.charAt(i) != '}') {
                return false;
            }
            if (s.charAt(i) == '[' || s.charAt(i) == '(' || s.charAt(i) == '{') {
                t = t.concat(s.charAt(i) + "");
            } else {
                if (t.length() == 0) {
                    return false;
                }
                char c = t.charAt(t.length() - 1);
                if ((s.charAt(i) == ']' && c != '[') || (s.charAt(i) == '}' && c != '{') || (s.charAt(i) == ')' && c != '(')) {
                    return false;
                }
                t = t.substring(0, t.length() - 1);
            }
        }
        return t.length() == 0;
    }
}
