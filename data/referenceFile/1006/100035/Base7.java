public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        int remainder, dividence = num;
        String retval = "";
        while (dividence >= 7 || dividence <= -7) {
            remainder = dividence % 7;
            dividence = dividence / 7;
            if (remainder < 0) {
                remainder *= -1;
            }
            retval = String.valueOf(remainder) + retval;
        }
        retval = String.valueOf(dividence) + retval;
        return retval;
    }
}
