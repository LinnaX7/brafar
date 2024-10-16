import java.util.*;
import java.lang.Math;

class ScientificNotation {

    public static double getValueFromAeB(String str) {
        String[] temp = str.split("e");
        double A = Double.parseDouble(temp[0]);
        double B = Double.parseDouble(temp[1]);
        double X = A * Math.pow(10, B);
        return X;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input X = ");
        String str = sc.next();
        double X = getValueFromAeB(str);
        System.out.println("The real value = " + X);
    }
}
