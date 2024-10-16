public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        int i = 0;
        boolean cut = false;
        String out = "";
        for (; i < str.length(); i++) {
            String substr = str.substring(i + 1, str.length());
            if (!substr.contains(String.valueOf(str.charAt(i)))) {
                out = str.substring(i, str.length());
                break;
            }
        }
        return out;
    }
}
