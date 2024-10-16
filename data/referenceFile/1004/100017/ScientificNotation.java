import java.util.Scanner;

class ScientificNotation {

    public static double getValueFromAeB(String s) {
        double x = 1;
        double ans = 0.0;
        int exp = 0;
        String[] arr = s.split("e", 2);
        for (char c : arr[0].toCharArray()) {
            if (c >= '0' && c <= '9') {
                ans += x * (c - 48);
                x /= 10;
            }
        }
        char[] charray = arr[1].toCharArray();
        for (char c : arr[1].toCharArray()) {
            if (c >= '0' && c <= '9') {
                exp += (c - 48);
                exp *= 10;
            }
        }
        exp /= 10;
        for (int i = 0; i < exp; i++) {
            if (charray[0] == '-') {
                ans /= 10;
            } else {
                ans *= 10;
            }
        }
        if (s.charAt(0) == '-') {
            ans *= -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a scientific  notation:");
        String sn = in.nextLine();
        getValueFromAeB(sn);
    }
}
