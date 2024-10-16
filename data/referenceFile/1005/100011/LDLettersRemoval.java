public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        char[] ch = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            ch[i] = str.charAt(i);
        }
        // System.out.println(ch);
        int j;
        String retstr;
        for (j = 0; j < str.length(); j++) {
            boolean t = false;
            for (int i = j + 1; i < str.length(); i++) {
                if (ch[j] == ch[i]) {
                    t = true;
                    // System.out.println(ch[j]);
                } else {
                    if (i == str.length() - 1 && !t) {
                        // System.out.println(j);
                        break;
                    }
                }
            }
            if (!t) {
                break;
            }
        }
        retstr = str.substring(j);
        // System.out.println(retstr);
        return retstr;
    }
}
