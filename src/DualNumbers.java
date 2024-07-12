public class DualNumbers extends SingleNumber{
    protected final long secondNumber;

    public DualNumbers(long firstNumber, long secondNumber) {
        super(firstNumber);
        this.secondNumber = secondNumber;
    }

    @Override
    public void process() {
        if (!isNatural(secondNumber))
            secondNumberErrorMessage();
        else
            multipleNumber();
    }

    public void multipleNumber() {
        for (long i = usersNumber; i < secondNumber + usersNumber; i++) {
            System.out.println(numberInfo(i));
        }
    }

    static void secondNumberErrorMessage() {
        System.out.println("\nThe second parameter should be a natural number.");
    }

    @Override
    public String numberInfo(long usersNumber){
        String characteristic = " is " +
                (isBuzz(usersNumber)?"buzz, ":"") +
                (isDuck(usersNumber)?"duck, ":"") +
                (isGapful(usersNumber)?"gapful, ":"") +
                (isPalindrome(usersNumber)?"palindromic, ":"") +
                (isSpy(usersNumber)?"spy, ":"") +
                (isSquare(usersNumber)?"square, ":"") +
                (isSunny(usersNumber)?"sunny, ":"") +
                (isJumping(usersNumber)?"jumping, ":"") +
                (isHappy(usersNumber)?"happy, ":"sad, ") +
                (isEven(usersNumber)?"even":"odd");

        return String.format("\t\t " + usersNumber + characteristic);
    }
}
