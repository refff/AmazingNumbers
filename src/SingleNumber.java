import java.util.Arrays;

public class SingleNumber {
    protected final long usersNumber;

    public SingleNumber(long usersNumber) {
        this.usersNumber = usersNumber;
    }

    static boolean isNatural(long number) {
        return number >= 0;
    }

    static boolean isEven(long usersNumber) {
        return usersNumber % 2 == 0;
    }

    static boolean isBuzz(long usersNumber) {
        long num = usersNumber;
        long lastDigit = num % 10;
        long remainedNumber = num / 10;
        long diff = remainedNumber - (2 * lastDigit);

        return diff % 7 == 0 || lastDigit == 7;
    }

    static boolean isDuck(long usersNumber) {
        String value = String.valueOf(usersNumber);
        return value.contains("0");
    }

    static boolean isPalindrome(long usersNumber) {
        String number = String.valueOf(usersNumber);
        if (number.equals(new StringBuilder(number).reverse().toString())){
            return true;
        }
        return false;

    }

    static boolean isGapful(long usersNumber) {
        if (usersNumber > 99){
            String str = String.valueOf(usersNumber);
            String fnl = str.charAt(0) + "" + str.charAt(str.length() - 1);
            long fnlD = Long.valueOf(fnl);

            return usersNumber % fnlD == 0;
        } else
            return false;
    }

    static boolean isSpy(long usersNumber) {
        if (usersNumber < 10) return true;

        long sum = 0;
        long product = 1;

        while(usersNumber > 0) {
            long temp = usersNumber % 10;
            sum += temp;
            product *= temp;
            usersNumber /= 10;
        }

        return product == sum;
    }

    static boolean isSquare(long usersNumber) {
        return Math.sqrt(usersNumber) % 1 == 0;
    }

    static boolean isSunny(long usersNumber) {
        return isSquare(usersNumber + 1);
    }

    static boolean isJumping(long usersNumber) {
        long current;
        long previous = (usersNumber % 10);
        usersNumber /= 10;

        while (usersNumber > 0) {
            current = usersNumber % 10;

            if (!(current + 1 == previous || current - 1 == previous)) {
                return false;
            }

            usersNumber /= 10;
            previous = current;
        }

        return true;
    }

    static boolean isHappy(long usersNumber) {
        long fast = usersNumber;
        long slow = usersNumber;

        do {
            slow = Square(slow);
            fast = Square(Square(fast));
        } while (fast != slow);

        return slow == 1;
    }

    private static int Square(long n) {
        int answer = 0;

        while (n > 0) {
            int lastDigit = (int)n % 10;
            n /= 10;
            answer += (lastDigit * lastDigit);
        }

        return answer;
    }

    public void process() {
        System.out.println(numberInfo(usersNumber));
    }

    public String numberInfo(long usersNumber) {
        String info = String.format(
                "\nProperties of %d\n" +
                        "        even: %b\n" +
                        "         odd: %b\n" +
                        "        buzz: %b\n" +
                        "        duck: %b\n" +
                        "      gapful: %b\n" +
                        "         spy: %b\n" +
                        "      square: %b\n" +
                        "       sunny: %b\n" +
                        "     jumping: %b\n" +
                        "       happy: %b\n" +
                        " palindromic: %b",
                usersNumber,
                isEven(usersNumber),
                !isEven(usersNumber),
                isBuzz(usersNumber),
                isDuck(usersNumber),
                isGapful(usersNumber),
                isSpy(usersNumber),
                isSquare(usersNumber),
                isSunny(usersNumber),
                isJumping(usersNumber),
                isHappy(usersNumber),
                isPalindrome(usersNumber));
        return info;
    }
}
