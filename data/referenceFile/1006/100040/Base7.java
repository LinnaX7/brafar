import java.lang.reflect.Array;

public class Base7 {

    public static String convertToBase7(int num) {
        // Check if variable num = 0, then return 0
        if (num == 0) {
            return "0";
        }
        // Initializing variables
        int i = 0;
        int rem = 0;
        int n = 0;
        int signCheck = num;
        int tempInt = 0;
        char tempChar;
        String tempStr = "";
        char[] base7Str = new char[8];
        // Check sign
        if (signCheck < 0) {
            num = (-1) * num;
        }
        // For loop for converting number with base 10 to base 7
        for (i = 7; i >= 0; i--) {
            tempInt = (int) (num / Math.pow(7, i));
            base7Str[n] = Integer.toString(tempInt).charAt(0);
            rem = (int) (num % Math.pow(7, i));
            num = rem;
            n++;
        }
        // Convert char array to integer
        tempInt = Integer.parseInt(new String(base7Str));
        // Check for sign and if negative mulitply by -1
        if (signCheck < 0) {
            tempInt = (-1) * tempInt;
        }
        // Convert integer to string
        tempStr = Integer.toString(tempInt);
        // Return the string containing the input number as a number of base 7 as a string
        return tempStr;
    }
}
