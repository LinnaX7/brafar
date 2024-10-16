import java.util.Scanner;

public class ScientificNotation {

    public static double getValueFromAeB(String scientific_notation) {
        String[] temp = scientific_notation.split("e");
        double A = Double.parseDouble(temp[0]);
        double B = Double.parseDouble(temp[1]);
        double result = A * Math.pow(10, B);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter a number:");
        int sciNotation = sc.nextInt();
        double result = getValueFromAeB(String.valueOf(sciNotation));
        System.out.println(sciNotation + " = " + result);
    }
}
