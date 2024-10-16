import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        // if 'num' is a negative number...
        boolean negative_flag = false;
        if (num < 0) {
            negative_flag = true;
            // then we treat it as a positive number but set a flag
            num = num * (-1);
        }
        int num_temp = num, count = 0;
        // the 'count' is stored the number that the 'num' can be divided by 7 how many times
        while (num_temp >= 7) {
            num_temp /= 7;
            count++;
        }
        int[] iArr = new int[count + 1];
        int remainder = 0;
        while (count >= 0) {
            remainder = num % 7;
            iArr[count] = remainder;
            count--;
            num /= 7;
        }
        /* For example, if 'num' is 100, the 'count' is  2 (100/ (7^2) < 7)
        * and the 'remainder' is (100 % 7 = 2)
        *
        * remainder: 0      2        0          2
        * iArr:      {,,}   {,, 2}   {, 0, 2}  {2, 0, 2}
        * count:     3      2        1          0
        * num:       100    14       2          0
        */
        String retStr = "";
        if (negative_flag) {
            // if 'num' if negative, we initialize the 'retStr' is "-";
            retStr = "-";
        }
        int len_arr = iArr.length;
        for (int index = 0; index < len_arr; index++) {
            // convert one value in 'iArr' to a String object 'temp' in each loop
            String temp = String.valueOf(iArr[index]);
            // concatenate the 'retStr' and the 'temp' and assigning it to the 'retStr'
            retStr = retStr.concat(temp);
        }
        /* For example(Continue)
        * retStr: "" (because 202 is a positive number) "2" "02" "202"
        * temp: 2 0 2
        */
        return retStr;
    }
}
