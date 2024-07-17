package numberGame;

import java.util.Random;
import java.util.Scanner;

public class GuessingNumberGame {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        
        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int number = random.nextInt(100) + 1;
            int attempts = 10;
            int roundAttempts = 0;

            System.out.println("\nI have generated a number between 1 and 100. Can you guess it?");
            System.out.println("You have " + attempts + " attempts.");

            while (attempts > 0) {
                System.out.print("Enter your guess: ");
                String input = scanner.nextLine();

                // Check if the input is a valid number
                if (!isNumeric(input)) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                int guess = Integer.parseInt(input);
                roundAttempts++;
                attempts--;

                if (guess < number) {
                    System.out.println("Too low!");
                } else if (guess > number) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You guessed the number " + number + " correctly in " + roundAttempts + " attempts.");
                    break;
                }

                if (attempts == 0) {
                    System.out.println("Sorry, you've used all your attempts. The number was " + number + ".");
                }
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! Goodbye!");
        scanner.close();
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
