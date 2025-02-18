public class SpecialNumber {

    public static boolean isSpecial(int num) {
        // Task 3: Return true if and only if 'num' is special
        // the number of prime factors
        int factors = 0;
        int target = num;
        for (int i = 2; i < num / 2; i++) {
            if (isPrimeNumber(i)) {
                // prime number
                // store the intermediate value
                int target2;
                target2 = middleValue(target, i);
                if (target2 != target) {
                    // judge if the prime number is one of prime factors
                    // get the value which is completely divided by a prime factor
                    target = target2;
                    factors += 1;
                }
            }
        }
        if (target == 1 && factors == 3) {
            return true;
        }
        return false;
    }

    public static boolean isPrimeNumber(int number) {
        // determine if a number is prime number
        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int middleValue(int a, int b) {
        // calculate a % b until the result is not equal to 0, return a
        while (a % b == 0) {
            a = a / b;
        }
        return a;
    }
}
