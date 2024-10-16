public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        char[] charStr = str.toCharArray();
        int current = 0;
        boolean isDup = true;
        while ((isDup == true) && (current < str.length())) {
            isDup = false;
            for (int i = current + 1; i < str.length(); i++) {
                if (charStr[current] == charStr[i]) {
                    isDup = true;
                    break;
                }
            }
            current++;
        }
        if (isDup == false) {
            String newStr = new String(charStr, current - 1, str.length() - current + 1);
            return (newStr);
        }
        return "";
    }
}
