public class LDLettersRemoval {

    public static String removeLDLetters(String s) {
        // 0, special case : empty string
        if (s.isEmpty()) {
            return s;
        }
        // 1, find the index of first leading letter...
        int index = 0;
        int length = s.length();
        boolean found = false;
        while (!found) {
            found = true;
            char ch = s.charAt(index);
            for (int i = index + 1; i < length; ++i) {
                if (s.charAt(i) == ch) {
                    found = false;
                    break;
                }
            }
            if (!found) {
                ++index;
            }
        }
        // 2, return the related substring
        return s.substring(index);
    }
}
