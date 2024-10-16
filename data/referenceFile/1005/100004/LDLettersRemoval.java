public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        int len = str.length();
        char getChar;
        for (int i = 0; i < len; i++) {
            getChar = str.charAt(i);
            boolean flag = false;
            for (int j = i + 1; j < len; j++) {
                if (str.charAt(j) == getChar) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return str.substring(i);
            }
        }
        return "";
    }
}
