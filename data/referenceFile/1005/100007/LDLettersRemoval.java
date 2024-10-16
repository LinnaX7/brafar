public class LDLettersRemoval {

    public static String removeLDLetters(String str) {
        // TODO: Add your code here
        int n = str.length();
        for (int i = 0; i < n; i++) {
            int key = 0;
            for (int j = 1; j < n; j++) {
                if (str.charAt(j) == str.charAt(key)) {
                    str = str.substring(0, key) + str.substring(key + 1);
                    key = j - 1;
                    j -= 1;
                    n -= 1;
                }
            }
        }
        return str;
    }
}
