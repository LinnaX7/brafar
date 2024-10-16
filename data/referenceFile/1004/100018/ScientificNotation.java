public class ScientificNotation {

    public static double getValueFromAeB(String aeb) {
        int b = 0;
        double a = 0, value = 0;
        String stra = "", strb = "";
        int i = 0;
        do {
            stra += aeb.charAt(i);
            i++;
        } while (aeb.charAt(i) != 'e');
        a = Double.parseDouble(stra.trim());
        i++;
        while (i < aeb.length()) {
            strb += aeb.charAt(i);
            i++;
        }
        b = Integer.parseInt(strb);
        double db = 1;
        if (b < 0) {
            b *= -1;
            for (i = 0; i < b; i++) {
                db /= 10.0;
            }
        } else {
            for (i = 0; i < b; i++) {
                db *= 10;
            }
        }
        value = a * db;
        return value;
    }
}
