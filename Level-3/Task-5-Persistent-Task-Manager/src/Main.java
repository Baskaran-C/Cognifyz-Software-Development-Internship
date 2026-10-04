import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Task 5: Persistent CRUD Application Using File I/O
 * Cognifyz Technologies - Software Development Internship (Level 3)
 *
 * Objective: Enhance Task 3 CRUD application to store task data persistently in a text file (`tasks.txt`),
 * demonstrating File I/O, error handling, BufferedReader/BufferedWriter, and data persistence across sessions.
 */
public class Main {
    private static final String FILE_PATH = "tasks.txt";

    public static void main(String[] args) {
        TaskFileManager fileManager = new TaskFileManager(FILE_PATH);
        List<Task> tasks = fileManager.loadTasks();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Determine highest existing ID to avoid collisions
        int nextId = 1;
        for (Task t : tasks) {
            if (t.getId() >= nextId) {
                nextId = t.getId() + 1;
            }
        }

        // If tasks list is fresh, seed default tasks
        if (tasks.isEmpty()) {
            tasks.add(new Task(nextId++, "Review Java File I/O Docs", "Read BufferedReader and BufferedWriter APIs", "Completed"));
            tasks.add(new Task(nextId++, "Implement Task 5 Persistence", "Save & load tasks from tasks.txt file", "Completed"));
            tasks.add(new Task(nextId++, "Build Interactive Scraper", "Develop Task 6 web scraping utility", "In Progress"));
            fileManager.saveTasks(tasks);
        }

        while (running) {
            System.out.println("\n=================================================");
            System.out.println("   COGNIFYZ PERSISTENT TASK MANAGER (TASK 5)     ");
            System.out.println("=================================================");
            System.out.println("1. Create New Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Task Details / Status");
            System.out.println("4. Delete Task");
            System.out.println("5. Reload Tasks from Disk");
            System.out.println("6. Save & Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter choice (1-6): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice! Enter a number between 1 and 6.");
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

                    Task newTask = new Task(nextId++, title, desc, status.isEmpty() ? "Pending" : status);
                    tasks.add(newTask);
                    fileManager.saveTasks(tasks);
                    System.out.println(" SUCCESS: Task #" + newTask.getId() + " added and saved to disk.");
                    break;

                case 2:
                    System.out.println("\n--- VIEW ALL TASKS (LOADED FROM DISK) ---");
                    if (tasks.isEmpty()) {
                        System.out.println("No tasks currently stored.");
                    } else {
                        for (Task t : tasks) {
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

                    Task existing = null;
                    for (Task t : tasks) {
                        if (t.getId() == updateId) {
                            existing = t;
                            break;
                        }
                    }

                    if (existing == null) {
                        System.out.println(" Task #" + updateId + " not found.");
                        break;
                    }

                    System.out.println("Current: " + existing);
                    System.out.print("Enter New Title (leave blank to keep): ");
                    String newTitle = scanner.nextLine();
                    if (!newTitle.trim().isEmpty()) existing.setTitle(newTitle);

                    System.out.print("Enter New Description (leave blank to keep): ");
                    String newDesc = scanner.nextLine();
                    if (!newDesc.trim().isEmpty()) existing.setDescription(newDesc);

                    System.out.print("Enter New Status (leave blank to keep): ");
                    String newStatus = scanner.nextLine();
                    if (!newStatus.trim().isEmpty()) existing.setStatus(newStatus);

                    fileManager.saveTasks(tasks);
                    System.out.println(" SUCCESS: Task updated and synced to " + FILE_PATH);
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

                    Task toDelete = null;
                    for (Task t : tasks) {
                        if (t.getId() == deleteId) {
                            toDelete = t;
                            break;
                        }
                    }

                    if (toDelete != null) {
                        tasks.remove(toDelete);
                        fileManager.saveTasks(tasks);
                        System.out.println(" SUCCESS: Task #" + deleteId + " removed from file.");
                    } else {
                        System.out.println(" Task #" + deleteId + " not found.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- RELOADING FROM DISK ---");
                    tasks = fileManager.loadTasks();
                    System.out.println("Tasks refreshed successfully.");
                    break;

                case 6:
                    fileManager.saveTasks(tasks);
                    System.out.println("All data safely saved to " + FILE_PATH + ". Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter a choice between 1 and 6.");
            }
        }
        scanner.close();
    }
}
