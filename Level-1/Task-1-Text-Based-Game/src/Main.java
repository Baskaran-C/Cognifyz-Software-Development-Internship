import java.util.Random;
import java.util.Scanner;

/**
 * Task 1: Basic Text-Based Game (Number Guessing Game)
 * Cognifyz Technologies - Software Development Internship (Level 1)
 *
 * Objective: Demonstrate conditional statements (if, else if, else), loops (while),
 * Scanner input, and Random number generation.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=================================================");
        System.out.println("     COGNIFYZ NUMBER GUESSING GAME (TASK 1)      ");
        System.out.println("=================================================");
        System.out.println("I've chosen a secret number between 1 and 100.");
        System.out.println("Can you guess what it is?");
        System.out.println("-------------------------------------------------");

        int targetNumber = random.nextInt(100) + 1;
        int attempts = 0;
        boolean guessedCorrectly = false;

        while (!guessedCorrectly) {
            System.out.print("Enter your guess (1-100): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next(); // Clear invalid input
                continue;
            }

            int guess = scanner.nextInt();
            attempts++;

            if (guess < 1 || guess > 100) {
                System.out.println("Out of bounds! Please guess a number between 1 and 100.");
            } else if (guess > targetNumber) {
                System.out.println("Too High! Try a smaller number.");
            } else if (guess < targetNumber) {
                System.out.println("Too Low! Try a larger number.");
            } else {
                guessedCorrectly = true;
                System.out.println("-------------------------------------------------");
                System.out.println(" CONGRATULATIONS! You guessed the correct number!");
                System.out.println(" Secret Number: " + targetNumber);
                System.out.println(" Total Attempts: " + attempts);
                System.out.println(" Performance: " + getRating(attempts));
                System.out.println("=================================================");
            }
        }
        scanner.close();
    }

    private static String getRating(int attempts) {
        if (attempts <= 3) return "Master Guesser (Exceptional!)";
        if (attempts <= 7) return "Great Job (Sharp intuition!)";
        if (attempts <= 10) return "Good Effort (Well done!)";
        return "Keep Practicing (You'll get faster!)";
    }
}
