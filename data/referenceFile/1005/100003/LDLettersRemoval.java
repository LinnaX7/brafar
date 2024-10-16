public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        char[] c = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            c[i] = str.charAt(i);
        }
        int v;
        String str2;
        for (v = 0; v < str.length(); v++) {
            boolean f = false;
            for (int i = v + 1; i < str.length(); i++) {
                if (c[v] == c[i]) {
                    f = true;
                } else {
                    if (i == str.length() - 1 && !f) {
                        break;
                    }
                }
            }
            if (!f) {
                break;
            }
        }
        str2 = str.substring(v);
        return str2;
    }
}
