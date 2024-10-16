import java.util.ArrayList;
import java.util.List;

public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        if (str.length() == 0) {
            // whether it is empty
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            // only 6 acceptable characters
            if (str.charAt(i) != '(' && str.charAt(i) != ')' && str.charAt(i) != '{' && str.charAt(i) != '}' && str.charAt(i) != '[' && str.charAt(i) != ']') {
                return false;
            }
        }
        // the balanced checking begins
        // Idea of this partially comes from the lecture 3 of COMP2011
        // Reference: https://learn.polyu.edu.hk/ultra/courses/_94507_1/cl/outline
        // an array of the str, easy to edit
        char[] charStr = str.toCharArray();
        // An array list is used to work as the stack, store the left-side brackets
        List<Character> myList = new ArrayList();
        for (int i = 0; i < str.length(); i++) {
            // for the left-sides, store to the list with the newest one first.
            if (charStr[i] == '(' || charStr[i] == '{' || charStr[i] == '[') {
                myList.add(0, charStr[i]);
            } else // for the right-sides, first check whether the list is empty (no left-side to pair)
            {
                // and if the newest right-side one is matched with the left-side, remove them
                if (charStr[i] == ')') {
                    if (myList.size() != 0) {
                        if (myList.get(0) == '(') {
                            myList.remove(0);
                            continue;
                        }
                    }
                    return false;
                }
                if (charStr[i] == '}') {
                    if (myList.size() != 0) {
                        if (myList.get(0) == '{') {
                            myList.remove(0);
                            continue;
                        }
                    }
                    return false;
                }
                if (charStr[i] == ']') {
                    if (myList.size() != 0) {
                        if (myList.get(0) == '[') {
                            myList.remove(0);
                            continue;
                        }
                    }
                    return false;
                }
            }
        }
        // check whether there is single, unpaired element in the lists.
        return myList.size() == 0;
    }
}
