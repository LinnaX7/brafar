public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        // Initialization of variables
        int exponent = 0;
        int i = 0;
        double significand = 0.0, retValue;
        int length = strSequence.length();
        String tempStr;
        String temp;
        // Create for loop to get the length of string until e
        while (true) {
            if (strSequence.charAt(i) == 'e') {
                break;
            }
            i++;
        }
        // Get the significand, ex: [7.56]e-1
        tempStr = strSequence.substring(0, i);
        significand = Double.parseDouble(tempStr);
        // Get the exponent, ex: 7.56e[-1]
        tempStr = strSequence.substring(length - 2, length);
        // Check if minus
        if (tempStr.charAt(0) == '-') {
            temp = tempStr.substring(1);
            exponent = (-1) * Integer.parseInt(temp);
            // exponent = (-1) * tempStr.charAt(1);
        } else {
            temp = tempStr.substring(1);
            exponent = (1) * Integer.parseInt(temp);
            // exponent = Double.parseDouble(tempStr.charAt(1));
        }
        // Get the corresponding real value from the scientific notation
        retValue = significand * Math.pow(10, exponent);
        // Return the value
        return retValue;
    }
}
