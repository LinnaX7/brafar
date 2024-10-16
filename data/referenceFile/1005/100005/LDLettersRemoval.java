public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        int index = 0;
        for (index = 0; index < str.length(); index++) {
            boolean delete_Check = false;
            /* delete check is used as to check whether the first letter needs to be removed
                if not, then the repetition will directly break.
                if it needs, then the first letter will be ignored, the second letter will
                repeat the process. and finally we get the position of the letter which does not
                need to be moved, then, return a new string begin from the final correct position.
             */
            for (int inner_index = 1 + index; inner_index < str.length(); inner_index++) {
                if (str.charAt(index) == str.charAt(inner_index)) {
                    inner_index = str.length();
                    delete_Check = true;
                } else {
                    delete_Check = false;
                }
            }
            if (delete_Check == false) {
                break;
            }
        }
        String str1 = new String("");
        for (index = index; index < str.length(); index++) {
            str1 = str1 + str.charAt(index);
        }
        str = str1;
        return str;
    }
}
