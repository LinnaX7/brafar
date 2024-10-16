public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        while (str.length() != 0) {
            if (str.substring(1).contains(str.substring(0, 1))) {
                str = str.substring(1);
                continue;
            }
            break;
        }
        return str;
    }
}
