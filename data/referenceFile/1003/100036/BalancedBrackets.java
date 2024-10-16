public class BalancedBrackets {

    static char matchBrack(char brack) {
        // Return the opening bracket matching the closing bracket
        if (brack == ')')
            return '(';
        if (brack == ']')
            return '[';
        if (brack == '}')
            return '{';
        else
            return 'e';
    }

    static char[] popElements(char[] l, int index1, int index2) {
        // Pop two elements from an array
        int n = l.length;
        char[] result = new char[n - 2];
        int counter = 0;
        for (int i = 0; i < n - 2; ) {
            if (counter == index1 || counter == index2) {
                counter++;
                continue;
            }
            result[i] = l[counter];
            counter++;
            i++;
        }
        return result;
    }

    static boolean isBrack(char x) {
        // Determine whether a character is one of the six brackets
        char[] brackets = { '(', ')', '[', ']', '{', '}' };
        boolean flag = false;
        for (char element : brackets) {
            if (x == element) {
                flag = true;
            }
        }
        return flag;
    }

    static boolean isBrackBal(char[] charList) {
        // Determine whether the brackets are balanced
        int counter = 1;
        while (counter < charList.length) {
            if (counter == 0) {
                // To avoid the array index out of range
                counter++;
                continue;
            }
            if (charList[counter - 1] == matchBrack(charList[counter])) {
                // If the two brackets are matched, then pop them
                charList = popElements(charList, counter - 1, counter);
                counter -= 2;
            }
            counter++;
        }
        if (charList.length == 0) {
            return true;
        } else
            return false;
    }

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        char[] charList = str.toCharArray();
        // If empty
        if (charList.length == 0) {
            return false;
        }
        // If contains other characters than parentheses
        for (char c : charList) {
            if (!isBrack(c)) {
                return false;
            }
        }
        // Balanced or not
        return isBrackBal(charList);
    }
}
