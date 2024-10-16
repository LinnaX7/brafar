public class ScientificNotation {

    public static double getValueFromAeB(String strSequence) {
        String[] a = strSequence.split("e");
        double exponent = Double.parseDouble(a[1]);
        double significand = Double.parseDouble(a[0]);
        double plus = 1;
        double abs = (exponent >= 0 ? exponent : -exponent);
        for (int i = 0; i < abs; i++) {
            plus *= (exponent > 0 ? 10 : 0.1);
        }
        double sN = significand * plus;
        return sN;
    }
}

class TestScientificNotation {

    public static void main(String[] args) {
        ScientificNotation test1 = new ScientificNotation();
        System.out.println(test1.getValueFromAeB("-8.05e1"));
        ScientificNotation test2 = new ScientificNotation();
        System.out.println(test2.getValueFromAeB("1.7e-2"));
    }
}
