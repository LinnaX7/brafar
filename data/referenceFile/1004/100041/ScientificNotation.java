import java.util.Scanner;

public class ScientificNotation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input:");
        String strSequence = scanner.next();
        double s = getValueFromAeB(strSequence);
        System.out.println(s);
    }

    public static double getValueFromAeB(String strSequence) {
        int exponent = 0;
        double significand = 0.0, retValue;
        int index = strSequence.indexOf("e");
        significand = Double.parseDouble(strSequence.substring(0, index));
        exponent = Integer.parseInt(strSequence.substring(index + 1));
        retValue = significand * Math.pow(10, exponent);
        return retValue;
    }
}
