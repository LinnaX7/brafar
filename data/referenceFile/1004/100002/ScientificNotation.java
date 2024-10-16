import java.util.Scanner;

class ScientificNotation {

    public static void main(String[] args) {
        System.out.println("Please input the scientific notation :");
        Scanner input = new Scanner(System.in);
        String value = input.next();
        System.out.println(getValueFromAeB(value));
    }

    public static double getValueFromAeB(String input) {
        String[] abc = input.split("e");
        double a = Double.parseDouble(abc[0]);
        double b = Double.parseDouble(abc[1]);
        double output;
        if (b < 0) {
            output = a / (Math.pow(10, Math.abs(b)));
        } else {
            output = a * (Math.pow(10, b));
        }
        return output;
    }
}
