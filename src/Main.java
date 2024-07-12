import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static long usersNumber;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        welcomeMessage();
        instructions();
        askMessage();
        goodbyeMessage();
    }

    static void welcomeMessage() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    static void goodbyeMessage() {
        System.out.println("\nGoodbye!");
    }

    static void errorMessage() {
        System.out.println("\nThe first parameter should be a natural number or zero.");
    }

    static void secondNumberErrorMessage() {
        System.out.println("\nThe second parameter should be a natural number.");
    }

    static void instructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }

    static void askMessage() {
        while(true){
            System.out.print("\nEnter a request: ");
            ArrayList<String> numbers = new ArrayList<>(List.of(scanner.nextLine().toUpperCase().split(" ")));

            try {
                usersNumber = Long.parseLong(numbers.getFirst());
            } catch (NumberFormatException e) {
                errorMessage();
                continue;
            }
            if (usersNumber == 0)
                break;

            classSelection(numbers);
        }
    }

    static void classSelection(ArrayList<String> numbers) {
        long firstNumber = Long.parseLong(numbers.getFirst());
        long secondNumber = 0;


        if (firstNumber < 0)
            errorMessage();
        else {
            if (numbers.size() == 1) {
                SingleNumber singleNumber = new SingleNumber(firstNumber);
                singleNumber.process();
            } else {
                try {
                    secondNumber = Long.parseLong(numbers.get(1));
                } catch (NumberFormatException e) {
                    secondNumberErrorMessage();
                    askMessage();
                }

                if (numbers.size() == 2){
                    DualNumbers dualNumbers = new DualNumbers(firstNumber, secondNumber);
                    dualNumbers.process();
                } else if (numbers.size() >= 3) {
                    ArrayList<String> parameters = new ArrayList<>(numbers.subList(2, numbers.size()));
                    NumbersByParameter numbersByParameter = new NumbersByParameter(firstNumber, secondNumber, parameters);
                    numbersByParameter.process();
                }
            }
        }
    }
}