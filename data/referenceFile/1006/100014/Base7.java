public class Base7 {

    public static String convertToBase7(int X) {
        if (X < 0) {
            return "-" + convertToBase7(-X);
        }
        if (X < 7 && X >= 0) {
            return Integer.toString(X);
        }
        return convertToBase7(X / 7) + Integer.toString(X % 7);
    }
}
