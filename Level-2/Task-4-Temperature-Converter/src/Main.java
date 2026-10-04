import java.util.Scanner;

/**
 * Task 4: Temperature Converter Program
 * Cognifyz Technologies - Software Development Internship (Level 2)
 *
 * Objective: Convert temperatures between Celsius, Fahrenheit, and Kelvin
 * with input validation and clear formatted output.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=================================================");
            System.out.println("     COGNIFYZ TEMPERATURE CONVERTER (TASK 4)    ");
            System.out.println("=================================================");
            System.out.println("1. Celsius to Fahrenheit & Kelvin");
            System.out.println("2. Fahrenheit to Celsius & Kelvin");
            System.out.println("3. Kelvin to Celsius & Fahrenheit");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Select conversion direction (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 4) {
                System.out.println("Exiting Temperature Converter. Goodbye!");
                running = false;
                break;
            }

            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please select from 1 to 4.");
                continue;
            }

            System.out.print("Enter temperature value: ");
            if (!scanner.hasNextDouble()) {
                System.out.println("Invalid number format! Please enter a valid decimal or integer value.");
                scanner.next();
                continue;
            }

            double temp = scanner.nextDouble();
            System.out.println("\n--- CONVERSION RESULTS ---");

            switch (choice) {
                case 1:
                    // Celsius -> Fahrenheit & Kelvin
                    double fFromC = (temp * 9.0 / 5.0) + 32.0;
                    double kFromC = temp + 273.15;
                    System.out.printf("Input Temp  : %.2f °C\n", temp);
                    System.out.printf("Fahrenheit  : %.2f °F\n", fFromC);
                    System.out.printf("Kelvin      : %.2f K\n", kFromC);
                    break;

                case 2:
                    // Fahrenheit -> Celsius & Kelvin
                    double cFromF = (temp - 32.0) * 5.0 / 9.0;
                    double kFromF = cFromF + 273.15;
                    System.out.printf("Input Temp  : %.2f °F\n", temp);
                    System.out.printf("Celsius     : %.2f °C\n", cFromF);
                    System.out.printf("Kelvin      : %.2f K\n", kFromF);
                    break;

                case 3:
                    // Kelvin -> Celsius & Fahrenheit
                    if (temp < 0) {
                        System.out.println("Warning: Temperature below Absolute Zero (0 Kelvin) is physically impossible!");
                    }
                    double cFromK = temp - 273.15;
                    double fFromK = (cFromK * 9.0 / 5.0) + 32.0;
                    System.out.printf("Input Temp  : %.2f K\n", temp);
                    System.out.printf("Celsius     : %.2f °C\n", cFromK);
                    System.out.printf("Fahrenheit  : %.2f °F\n", fFromK);
                    break;
            }
            System.out.println("--------------------------");
        }
        scanner.close();
    }
}
