import java.util.Scanner;

public class BalancedBrackets {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String stringToBeTested = input.nextLine();
        // System.out.println(isEmpty(stringToBeTested));
        // System.out.println(containsOnlySixCharacters(stringToBeTested));
        System.out.println(isBalanced(stringToBeTested));
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (isEmpty(str)) {
            return false;
        }
        if (!containsOnlySixCharacters(str)) {
            return false;
        }
        char[] array = new char[str.length()];
        int top = 0;
        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            if (a == '(' || a == '[' || a == '{') {
                array[top++] = a;
            }
            if (a == ')') {
                if (top == 0) {
                    return false;
                }
                if (array[--top] != '(') {
                    return false;
                }
                array[top] = ' ';
            }
            if (a == ']') {
                if (top == 0) {
                    return false;
                }
                if (array[--top] != '[') {
                    return false;
                }
                array[top] = ' ';
            }
            if (a == '}') {
                if (top == 0) {
                    return false;
                }
                if (array[--top] != '{') {
                    return false;
                }
                array[top] = ' ';
            }
        }
        return top == 0;
    }

    public static boolean isEmpty(String str) {
        return str.equals("");
    }

    public static boolean containsOnlySixCharacters(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '{' && str.charAt(i) != '}' && str.charAt(i) != '[' && str.charAt(i) != ']') {
                return false;
            }
        }
        return true;
    }
}
