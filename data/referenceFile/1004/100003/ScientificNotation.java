public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double retValue;
        double significand;
        // TODO:To parse significand and exponent from strSequence.
        String[] parts = strSequence.split("e");
        /**
         * It's not even at least a double class, I don't know how can I not use parse which is outside of string class?
         * I tried casting but it's not working
         * Inconvertible types; cannot cast 'java.lang.String' to 'double'
         * It would be appreciated if the appropriate method without using parse is sent to the assignment feedback, or a link/pointer to it.
         * I can parse to double and narrow cast but I must at least parse to double.
         */
        double sigFigPart = Double.parseDouble(parts[0]);
        double exponentPart = Double.parseDouble(parts[1]);
        // no casting necessary because parsed to double
        significand = sigFigPart;
        // cast narrowing double >> int
        exponent = (int) exponentPart;
        // for debugging
        // System.out.println(sigFigPart);
        // System.out.println(exponentPart);
        /*working code with just parse, but I don't think I am allowed to submit just this as my answer
        significand = Double.parseDouble(parts[0]);
        exponent = Integer.parseInt(parts[1]);
         */
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
