import java.util.Scanner;

/**
 * Task 2: Generate and Print Number Patterns
 * Cognifyz Technologies - Software Development Internship (Level 1)
 *
 * Objective: Utilize nested loops (for loops) and conditional logic to generate
 * various structural number and star patterns based on user input.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=================================================");
            System.out.println("      COGNIFYZ PATTERN GENERATOR (TASK 2)        ");
            System.out.println("=================================================");
            System.out.println("1. Increasing Number Triangle");
            System.out.println("2. Decreasing Number Triangle");
            System.out.println("3. Centered Star Pyramid");
            System.out.println("4. Palindromic Number Pyramid");
            System.out.println("5. Floyd's Number Triangle");
            System.out.println("6. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter your choice (1-6): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            if (choice == 6) {
                System.out.println("Exiting Pattern Generator. Thank you!");
                running = false;
                break;
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            System.out.print("Enter number of rows (1-15): ");
            int rows = scanner.nextInt();
            if (rows < 1 || rows > 20) {
                System.out.println("Please choose rows between 1 and 20.");
                continue;
            }

            System.out.println("\n--- GENERATED PATTERN ---");
            switch (choice) {
                case 1:
                    printIncreasingNumberTriangle(rows);
                    break;
                case 2:
                    printDecreasingNumberTriangle(rows);
                    break;
                case 3:
                    printStarPyramid(rows);
                    break;
                case 4:
                    printPalindromicPyramid(rows);
                    break;
                case 5:
                    printFloydsTriangle(rows);
                    break;
            }
            System.out.println("-------------------------");
        }
        scanner.close();
    }

    // Pattern 1: Increasing Number Triangle
    private static void printIncreasingNumberTriangle(int rows) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    // Pattern 2: Decreasing Number Triangle
    private static void printDecreasingNumberTriangle(int rows) {
        for (int i = rows; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    // Pattern 3: Centered Star Pyramid
    private static void printStarPyramid(int rows) {
        for (int i = 1; i <= rows; i++) {
            for (int space = 1; space <= rows - i; space++) {
                System.out.print(" ");
            }
            for (int star = 1; star <= (2 * i - 1); star++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // Pattern 4: Palindromic Number Pyramid
    private static void printPalindromicPyramid(int rows) {
        for (int i = 1; i <= rows; i++) {
            for (int space = 1; space <= rows - i; space++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            for (int j = i - 1; j >= 1; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    // Pattern 5: Floyd's Number Triangle
    private static void printFloydsTriangle(int rows) {
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }
}
