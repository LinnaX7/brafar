// import static java.lang.Math.abs;
// import static java.lang.Math.pow;
public class Base7 {

    public static String convertToBase7(int num) {
        int str = 0, m = 1;
        while (num != 0) {
            str += (num % 7) * m;
            num = num / 7;
            m *= 10;
        }
        return String.valueOf(str);
    }
}
