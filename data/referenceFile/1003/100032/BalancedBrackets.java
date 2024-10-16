// string usage
import java.lang.String;
// for stack usage
import java.util.*;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // convert strings to char array
        char[] stringArray = str.toCharArray();
        // bool value
        boolean isBalanced_str = true;
        int count = 0;
        if (str.isEmpty() == true) {
            // return false if str is empty
            isBalanced_str = false;
        }
        // if str is non-empty and not beyond 6, then evaluate balanced
        if (isBalanced_str == true) {
            // create a stack for checking isBalanced
            Stack my_stack = new Stack();
            for (int i = 0; i < stringArray.length; i++) {
                if (stringArray[i] != '(' && stringArray[i] != '{' && stringArray[i] != '[' && stringArray[i] != ')' && stringArray[i] != '}' && stringArray[i] != ']') {
                    // false if any char dose not belongs to those 6 kinds of symbols
                    return false;
                }
                if (stringArray[i] == '(' || stringArray[i] == '{' || stringArray[i] == '[') {
                    // left-side symbol add into Stack
                    my_stack.push(stringArray[i]);
                }
                if (stringArray[i] == ']') {
                    // which means the char ']' is redundant
                    if (my_stack.empty()) {
                        return false;
                    }
                    char symbol = (char) my_stack.pop();
                    // check balanced whether [ ] pair fulfill
                    if (symbol != '[') {
                        return false;
                    }
                }
                if (stringArray[i] == '}') {
                    // which means the char '}' is redundant
                    if (my_stack.empty()) {
                        return false;
                    }
                    char symbol = (char) my_stack.pop();
                    // check balanced whether { } pair fulfill
                    if (symbol != '{') {
                        return false;
                    }
                }
                if (stringArray[i] == ')') {
                    // which means the char ')' is redundant
                    if (my_stack.empty()) {
                        return false;
                    }
                    char symbol = (char) my_stack.pop();
                    // check balanced whether ( ) pair fulfill
                    if (symbol != '(') {
                        return false;
                    }
                }
            }
            // record whether stack is empty(true then is Balanced, false then not balanced)
            isBalanced_str = my_stack.empty();
        }
        // return bool value
        return isBalanced_str;
    }
}
