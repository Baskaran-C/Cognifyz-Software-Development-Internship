import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskFileManager {
    private final String filePath;

    public TaskFileManager(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("[FILE I/O INFO] Task file (" + filePath + ") not found. Creating a new file on first save.");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Task task = Task.fromFileFormat(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            }
            System.out.println("[FILE I/O SUCCESS] Loaded " + tasks.size() + " tasks from " + filePath);
        } catch (IOException e) {
            System.err.println("[FILE I/O ERROR] Failed to read tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public boolean saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            System.out.println("[FILE I/O SUCCESS] Saved " + tasks.size() + " tasks to " + filePath);
            return true;
        } catch (IOException e) {
            System.err.println("[FILE I/O ERROR] Failed to write tasks to file: " + e.getMessage());
            return false;
        }
    }
}
