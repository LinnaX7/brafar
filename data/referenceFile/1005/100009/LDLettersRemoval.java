// import java.util.*;
public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        String substr = "";
        boolean flag = false;
        if (str.length() == 1) {
            substr = str;
        } else {
            for (int i = 0; i < str.length() - 1; i++) {
                flag = false;
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(i) == str.charAt(j)) {
                        // 
                        flag = true;
                        break;
                    }
                }
                if (!flag || i == str.length() - 2 && flag) {
                    if (i == str.length() - 2 && flag) {
                        i = str.length() - 1;
                    }
                    substr = str.substring(i, str.length());
                    break;
                }
            }
        }
        return substr;
    }
}
