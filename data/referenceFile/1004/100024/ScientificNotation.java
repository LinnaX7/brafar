import java.lang.Math;

public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        String[] k = strSequence.split("e");
        exponent = Integer.parseInt(k[1]);
        significand = Double.parseDouble(k[0]);
        retValue = significand * Math.pow(10, exponent);
        // 10^exponent
        return retValue;
    }

    public static void main(String[] args) {
        String sample = "-8.05e1";
        // -8.05 x 10^1 =-80.5
        double retValue = getValueFromAeB(sample);
        System.out.println(sample + " = " + retValue);
        sample = "1.7e-2";
        // 1.7 x 10^-2 =0.017
        retValue = getValueFromAeB(sample);
        System.out.println(sample + " = " + retValue);
    }
}
