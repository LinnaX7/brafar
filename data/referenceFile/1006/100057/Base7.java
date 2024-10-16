public class Base7 {

    public static String convertToBase7(int num) {
        // TODO: Add your code here
        final int BASE = 7, ZERO = '0';
        int size = 1, sign = 1;
        int tempNum = BASE;
        int quotient;
        int temp;
        // sign correction
        if (num < 0) {
            num *= -1;
            sign = -1;
        }
        quotient = num;
        // array size
        for (; num > tempNum - 1; ) {
            tempNum *= BASE;
            size++;
        }
        // int array, hold digits in respective indexes
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            temp = 1;
            for (int j = 0; j < size - i - 1; j++) {
                temp *= BASE;
            }
            intArray[i] = 0;
            while (quotient - temp >= 0) {
                quotient -= temp;
                intArray[i]++;
            }
        }
        temp = 0;
        char[] charArray = new char[sign == 1 ? size : size + 1];
        if (sign == -1) {
            charArray[temp] = '-';
            temp++;
        }
        for (int i = 0; i < size; i++) {
            charArray[temp] = (char) (intArray[i] + ZERO);
            temp++;
        }
        return new String(charArray);
    }
}
