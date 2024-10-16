public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            String letter = str.substring(i, i + 1);
            String remainString = str.substring(i + 1);
            if (!remainString.contains(letter)) {
                result = letter + remainString;
                break;
            }
        }
        return result;
    }
}
