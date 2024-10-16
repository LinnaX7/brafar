import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        String str = "({)}";
        if (isBalanced(str))
            System.out.println("yes");
        else
            System.out.println("not");
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.isEmpty() || isNotLegal(str)) {
            return false;
        }
        Stack mystack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == '{' || str.charAt(i) == '[') {
                mystack.push(str.charAt(i));
            } else {
                char temp = mystack.peek().toString().charAt(0);
                if (!mystack.isEmpty()) {
                    mystack.pop();
                } else {
                    return false;
                }
                if (isMatch(temp, str.charAt(i))) {
                    ;
                } else {
                    return false;
                }
            }
        }
        if (mystack.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isMatch(char c1, char c2) {
        // judge char c1 and c2 is match or not
        if ((c1 == '(' && c2 == ')') || (c1 == '{' && c2 == '}') || (c1 == '[' && c2 == ']')) {
            return true;
        } else
            return false;
    }

    public static boolean isNotLegal(String str) {
        // judge the input str isNot Legal
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == ')' || str.charAt(i) == '{' || str.charAt(i) == '}' || str.charAt(i) == '[' || str.charAt(i) == ']') {
                ;
            } else {
                return true;
            }
        }
        return false;
    }
}
