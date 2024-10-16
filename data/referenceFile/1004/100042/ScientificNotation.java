public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        String[] es = strSequence.split(String.valueOf('e'));
        String numStr = es[0];
        significand = Double.parseDouble(numStr);
        String numIndex = es[1];
        exponent = Integer.parseInt(numIndex);
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
