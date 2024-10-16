public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        int len = str.length();
        int start = 0;
        boolean dup = true;
        char c1, c2;
        for (int i = 0; i < len && dup; ++i) {
            c1 = str.charAt(i);
            dup = false;
            for (int j = i + 1; j < len; ++j) {
                c2 = str.charAt(j);
                if (c1 == c2) {
                    dup = true;
                    start++;
                    break;
                }
            }
        }
        str = str.substring(start, len);
        return str;
    }
}
