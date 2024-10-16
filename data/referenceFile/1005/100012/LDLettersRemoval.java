public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        int i;
        boolean found;
        char temp;
        for (i = 0; i < str.length(); i++) {
            found = false;
            temp = str.charAt(i);
            for (int j = i + 1; j < str.length(); j++) {
                if (temp == str.charAt(j)) {
                    found = true;
                    break;
                }
            }
            if (found == false) {
                break;
            }
        }
        return str.substring(i, str.length());
    }
}
