import java.util.Scanner;

public class ScientificNotation {

    static double getValueFromAeB(String AeB) {
        String[] cal = AeB.split("e");
        double A = Double.parseDouble(cal[0]);
        double B = Double.parseDouble(cal[1]);
        double ans = A * Math.pow(10, B);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("Example:");
        System.out.println("-8.05e1" + " = " + getValueFromAeB("-8.05e1"));
        System.out.println("1.7e-2" + " = " + getValueFromAeB("1.7e-2"));
        Scanner uin = new Scanner(System.in);
        System.out.println("Input your value:");
        String ustr = uin.nextLine();
        System.out.println(ustr + " = " + getValueFromAeB(ustr));
    }
}
