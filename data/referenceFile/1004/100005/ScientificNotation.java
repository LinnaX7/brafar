// Student name: Chan Ho Yin
// Student ID: 19071028D
// Question 1
public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        double retValue;
        String[] sides = strSequence.split("e");
        String sign = sides[0];
        String expo = sides[1];
        Integer temp_exp = new Integer(expo);
        Double temp_significand = new Double(sign);
        Double significand = temp_significand;
        Integer exponent = temp_exp;
        // double significand = Double.valueOf(sign);
        // int exponent = Integer.valueOf(expo);
        // TODO:To parse significand and exponent from strSequence.
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }

    public static void main(String[] args) {
        String str = ("-8.05e1");
        System.out.println(getValueFromAeB(str));
    }
}
