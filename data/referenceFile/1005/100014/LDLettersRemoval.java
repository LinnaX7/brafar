public class LDLettersRemoval {

    // Not to add other method
    public static String removeLDLetters(String str) {
        int i = 0;
        while (i + 1 < str.length()) {
            char curCh = str.charAt(i);
            boolean isLD = false;
            for (int j = i + 1; j < str.length(); j++) {
                char ch = str.charAt(j);
                if (curCh == ch) {
                    isLD = true;
                    break;
                }
            }
            if (isLD) {
                i = i + 1;
            } else {
                break;
            }
        }
        return str.substring(i);
    }
}
