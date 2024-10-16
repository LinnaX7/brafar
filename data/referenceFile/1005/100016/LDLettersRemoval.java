public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        String letter;
        for (int x = 0; x < str.length(); ) {
            letter = str.substring(0, 1);
            str = str.substring(1, str.length());
            if (str.indexOf(letter) == -1) {
                str = letter + str;
                break;
            }
        }
        return str;
    }
}
