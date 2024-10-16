// 20050394d Lau Sin Man (Task 4)
import java.util.Scanner;
import java.util.Stack;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (!str.isEmpty()) {
            // condition 1: 'str' is not empty
            if (str.length() % 2 == 0) {
                // validation (since the brackets must be paired to be balanced)
                char[] conversion = str.toCharArray();
                for (char i : conversion) {
                    // condition 2: only 6 specific characters are accepted
                    if (i == '(' || i == '{' || i == '[' || i == ')' || i == '}' || i == ']') {
                        // condition 3: balanced brackets
                        Stack<Character> stack = new Stack<>();
                        for (int index = 0; index < str.length(); index++) {
                            char data = str.charAt(index);
                            if (data == '(' || data == '{' || data == '[') {
                                stack.push(data);
                            } else {
                                if (data == ')') {
                                    // check if the closing bracket matches with opening bracket at the top of the stack
                                    if (stack.isEmpty() || stack.pop() != '(') {
                                        return false;
                                    }
                                }
                            }
                            if (data == '}') {
                                if (stack.isEmpty() || stack.pop() != '{') {
                                    return false;
                                }
                            }
                            if (data == ']') {
                                if (stack.isEmpty() || stack.pop() != '[') {
                                    return false;
                                }
                            }
                        }
                        // return true if all closing brackets matches with opening brackets, else return false
                        return stack.isEmpty();
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a string: ");
        String input = scan.nextLine();
        if (isBalanced(input)) {
            System.out.println('"' + input + '"' + " is balanced");
        } else
            System.out.println('"' + input + '"' + " is not balanced");
    }
}
