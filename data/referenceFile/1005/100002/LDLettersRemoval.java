public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        int k;
        String sstr;
        for (k = 0; k < str.length(); k++) {
            boolean f = false;
            for (int i = k + 1; i < str.length(); i++) {
                if (ch[k] == ch[i]) {
                    f = true;
                    // System.out.println(ch[j]);
                } else {
                    if (i == str.length() - 1 && !f) {
                        // System.out.println(j);
                        break;
                    }
                }
            }
            if (!f) {
                break;
            }
        }
        sstr = str.substring(k);
        return sstr;
    }
}
