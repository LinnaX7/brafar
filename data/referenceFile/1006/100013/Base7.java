public class Base7 {

    public static String convertToBase7(int num) {
        // Determine the sign
        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num *= -1;
        }
        // Figure out how many digits the output will be (exponent = number of digits)
        double exponent = 0, test = 1;
        if (num == 0) {
            exponent = 1;
        } else {
            boolean temp = false;
            while (temp == false) {
                if (test <= num) {
                    exponent++;
                    test *= 7;
                    continue;
                }
                temp = true;
            }
        }
        // Create an empty char array to store the result
        char[] resultArray = new char[(int) exponent];
        // Conversion of input to base-7
        int dividend = num;
        for (int i = (int) exponent - 1; i >= 0; i--) {
            int quotient = dividend / 7;
            int remainder = dividend - (quotient * 7);
            // Change the dividend for calculating the next digit
            dividend = quotient;
            // Convert int datatype to char datatype
            resultArray[i] = (char) (remainder + '0');
        }
        // Convert char array into a string
        String result = String.valueOf(resultArray);
        // Check whether the input is negative, and append negative sign
        if (isNegative) {
            result = "-" + result;
        }
        return result;
    }
}
