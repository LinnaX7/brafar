public class BalancedBrackets {

    public static boolean isBalanced(String str) {
        // Task 4: Return true if and only if 'str' 1) is non-empty, 2) contains only
        // the six characters, and 3) is balanced.
        // declaring and initializing variables
        int i, l = str.length();
        // variable to store each character of string
        char c;
        if (// case 1 of string being empty
        l == 0 || str.equals("")) {
            return false;
        }
        for (i = 0; i < l; i++) {
            // case 2 of string containing characters other than 6 brackets
            c = str.charAt(i);
            if (c != '(' && c != ')' && c != '{' && c != '}' && c != '[' && c != ']') {
                return false;
            }
        }
        // extra case of string length being an odd number
        if (l % 2 != 0) {
            return false;
        }
        // checking if the string is balanced
        // checking for the occurence of (), {}, []
        // and replacing them with replaceAll() method and the necessary regex
        while (str.contains("()") || str.contains("{}") || str.contains("[]")) {
            str = str.replaceAll("\\(\\)", "");
            str = str.replaceAll("\\{}", "");
            str = str.replaceAll("\\[]", "");
        }
        // if the string is not empty, return false, else return true
        return str.length() == 0;
    }
}
