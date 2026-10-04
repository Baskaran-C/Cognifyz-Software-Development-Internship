import java.util.Scanner;

/**
 * Task 3: Console CRUD Task Manager Application
 * Cognifyz Technologies - Software Development Internship (Level 2)
 *
 * Objective: Demonstrate Create, Read, Update, and Delete operations using OOP & ArrayList.
 */
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Seed initial sample data for quick demonstration
        taskManager.addTask("Complete Java Internship Task 1", "Implement number guessing game", "Completed");
        taskManager.addTask("Complete Java Internship Task 2", "Implement number pattern generator", "Completed");
        taskManager.addTask("Build Console Task Manager", "Implement CRUD operations", "In Progress");

        while (running) {
            System.out.println("\n=================================================");
            System.out.println("      COGNIFYZ TASK MANAGER (TASK 3 CRUD)       ");
            System.out.println("=================================================");
            System.out.println("1. Create New Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Existing Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter choice (1-5): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice! Enter a number between 1 and 5.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\n--- CREATE NEW TASK ---");
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter Status (Pending / In Progress / Completed): ");
                    String status = scanner.nextLine();
                    Task newTask = taskManager.addTask(title, desc, status.isEmpty() ? "Pending" : status);
                    System.out.println(" SUCCESS: Task created with ID #" + newTask.getId());
                    break;

                case 2:
                    System.out.println("\n--- VIEW ALL TASKS ---");
                    if (taskManager.isEmpty()) {
                        System.out.println("No tasks found.");
                    } else {
                        for (Task t : taskManager.getAllTasks()) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- UPDATE TASK ---");
                    System.out.print("Enter Task ID to update: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid Task ID!");
                        scanner.next();
                        break;
                    }
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Task existing = taskManager.getTaskById(updateId);
                    if (existing == null) {
                        System.out.println(" Task with ID " + updateId + " not found.");
                        break;
                    }
                    System.out.println("Current: " + existing);
                    System.out.print("Enter New Title (leave blank to keep): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter New Description (leave blank to keep): ");
                    String newDesc = scanner.nextLine();
                    System.out.print("Enter New Status (leave blank to keep): ");
                    String newStatus = scanner.nextLine();

                    if (taskManager.updateTask(updateId, newTitle, newDesc, newStatus)) {
                        System.out.println(" SUCCESS: Task updated.");
                    } else {
                        System.out.println(" Update failed.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- DELETE TASK ---");
                    System.out.print("Enter Task ID to delete: ");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid Task ID!");
                        scanner.next();
                        break;
                    }
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    if (taskManager.deleteTask(deleteId)) {
                        System.out.println(" SUCCESS: Task #" + deleteId + " deleted.");
                    } else {
                        System.out.println(" Task with ID " + deleteId + " not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting Task Manager. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Choose between 1 and 5.");
            }
        }
        scanner.close();
    }
}
