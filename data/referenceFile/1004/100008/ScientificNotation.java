public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        // TODO:To parse significand and exponent from strSequence.
        String[] AeB = strSequence.split("e");
        /* use the split method to divide string input into two parts,
                                                      which are significand and exponent and then form array AeB*/
        // AeB[0] is actually the significand, but it is in string form
        String A = AeB[0];
        // AeB[1] is actually the exponent, but it is in string form
        String B = AeB[1];
        double significand = new Double(A);
        /*use Double constructor and let string A be the parameter so that we could
                                            get a double significand */
        int exponent = new Integer(B);
        /*use Integer constructor and let string B be the parameter so that we could
                                            get an integer exponent */
        double retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
