public class ScientificNotation {

    public static void main(String[] args) {
        String test01 = "1.7e-2";
        String test02 = "-8.05e1";
        double s1 = getValueFromAeB(test01);
        double s2 = getValueFromAeB(test02);
        System.out.println(s1);
        System.out.println(s2);
    }

    public static double getValueFromAeB(String sci_form) {
        boolean isPositiveIndex = true;
        double before_e;
        int after_e;
        for (int i = 0; i < sci_form.length(); i++) {
            if (sci_form.charAt(i) == 'e') {
                if (sci_form.charAt(i + 1) == '-') {
                    isPositiveIndex = false;
                }
            }
        }
        if (isPositiveIndex) {
            // 字符串分段搜索
            String str1 = sci_form.substring(0, sci_form.indexOf("e"));
            String str2 = sci_form.substring(str1.length() + 1, sci_form.length());
            // 字符串转化小数及整数格式
            before_e = Double.parseDouble(str1);
            after_e = Integer.parseInt(str2);
            return before_e * Math.pow(10, after_e);
        }
        String str1 = sci_form.substring(0, sci_form.indexOf("e"));
        String str2 = sci_form.substring(str1.length() + 2, sci_form.length());
        before_e = Double.parseDouble(str1);
        after_e = Integer.parseInt(str2);
        return before_e * Math.pow(10, -after_e);
    }
}
