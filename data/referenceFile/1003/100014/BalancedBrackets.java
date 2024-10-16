// 20080439D GENG Longling
// check with the closing brackets
public class BalancedBrackets {

    public static void main(String[] args) {
        System.out.println(isBalanced("(){"));
        System.out.println(isBalanced(""));
        System.out.println(isBalanced("("));
        System.out.println(isBalanced("()"));
        System.out.println(isBalanced("{}"));
        System.out.println(isBalanced("[]"));
        System.out.println(isBalanced("{)"));
        System.out.println(isBalanced("(){}"));
        System.out.println(isBalanced("({})"));
        System.out.println(isBalanced("(()[{}])"));
        System.out.println(isBalanced("(("));
        System.out.println(isBalanced("({)}"));
        System.out.println(isBalanced("((((())))"));
        System.out.println(isBalanced("((((()))))"));
        System.out.println(isBalanced("(((({}))))"));
        System.out.println(isBalanced("((([{}))))"));
        System.out.println(isBalanced("([([{}])])"));
        System.out.println(isBalanced("(((({)})))"));
        System.out.println(isBalanced("({)}[{}])"));
        System.out.println(isBalanced("{()()}"));
        System.out.println(isBalanced("({())}"));
        System.out.println(isBalanced("{}//"));
    }

    public static boolean isBalanced(String str) {
        if ((str.isEmpty()) || str.length() == 1) {
            return false;
        }
        // check each closing brackets from left to right
        // mark its paired unmarked adjacent brackets
        // check if it all marked
        int n = str.length();
        boolean[] mark_list = new boolean[n];
        for (int i = 0; i < n; i++) {
            mark_list[i] = false;
        }
        // check
        for (int i = 1; i <= n - 1; i++) {
            if (str.charAt(i) == ')') {
                for (int j = i - 1; j >= 0; j -= 2) {
                    if ((mark_list[j] == false) && str.charAt(j) == '(') {
                        mark_list[j] = true;
                        mark_list[i] = true;
                        break;
                    }
                }
            } else {
                if (str.charAt(i) == ']') {
                    for (int j = i - 1; j >= 0; j -= 2) {
                        if ((mark_list[j] == false) && str.charAt(j) == '[') {
                            mark_list[j] = true;
                            mark_list[i] = true;
                            break;
                        }
                    }
                } else {
                    if (str.charAt(i) == '}') {
                        for (int j = i - 1; j >= 0; j -= 2) {
                            if ((mark_list[j] == false) && str.charAt(j) == '{') {
                                mark_list[j] = true;
                                mark_list[i] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (mark_list[i] == false) {
                return false;
            }
        }
        return true;
    }
}
