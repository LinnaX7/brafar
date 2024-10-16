import java.lang.Math;

public class MiniFloat {

    public static final int MINI_FLOAT_SIZE = 8;

    public static float miniFloatFromString(String bitSequence) {
        String[] code = bitSequence.split("", 0);
        // Task 1: compute the miniFloat value from "bitSequence";
        String binexponent = bitSequence.substring(1, 5);
        String binnexponent = bitSequence.substring(2, 5);
        int decexponent;
        if (code[1].equals("0")) {
            decexponent = Integer.parseInt(binexponent, 2);
        } else {
            String twoscomp = binnexponent;
            twoscomp = twoscomp.replace("0", "2");
            twoscomp = twoscomp.replace("1", "0");
            twoscomp = twoscomp.replace("2", "1");
            decexponent = 1 + Integer.parseInt(twoscomp, 2);
            decexponent = -1 * (decexponent);
        }
        double times = Math.pow(2, Double.parseDouble(String.valueOf(decexponent)));
        // String[] code=bitSequence.split("",0);
        // System.out.println(times);
        // String intmantissa="1."+code[5]+code[6]+code[7];
        // float valuemantissa= Float.parseFloat(intmantissa);
        float valmantissa = (float) 1.0;
        float pointfive = (float) 0.5;
        for (int j = 5; j < 8; j++) {
            if (code[j].equals("1")) {
                valmantissa = valmantissa + pointfive;
            }
            pointfive = (float) (pointfive * 0.5);
        }
        // System.out.println(times);
        String flag = code[0];
        int neg;
        int firstsign = Integer.parseInt(flag, 2);
        if (firstsign == 1) {
            neg = -1;
        } else {
            neg = 1;
        }
        double answers = (times * valmantissa) * neg;
        // System.out.print("\n" +answers);
        return (float) answers;
    }

    public static int numIntegralMiniFloats() {
        // Task 2: return the number of integral miniFloat values
        int task2count = 0;
        String[] arrayoftask2 = getValidMiniFloatBitSequences();
        float decvalue;
        for (String jj : arrayoftask2) {
            decvalue = miniFloatFromString(jj);
            if (decvalue % 1 == 0) {
                task2count = task2count + 1;
            }
        }
        System.out.println(task2count);
        // The Output is 94
        return task2count;
    }

    /**
     * Get all valid bit sequences for miniFloat values.
     */
    private static String[] getValidMiniFloatBitSequences() {
        int nbrValues = (int) Math.pow(2, MINI_FLOAT_SIZE);
        String[] result = new String[nbrValues];
        for (int i = 0; i < nbrValues; i++) {
            result[i] = String.format("%" + MINI_FLOAT_SIZE + "s", Integer.toBinaryString(i)).replace(' ', '0');
        }
        return result;
    }
}
