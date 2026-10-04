import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private int nextId;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.nextId = 1;
    }

    // Create Operation
    public Task addTask(String title, String description, String status) {
        Task task = new Task(nextId++, title, description, status);
        tasks.add(task);
        return task;
    }

    // Read Operations
    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    // Update Operation
    public boolean updateTask(int id, String newTitle, String newDescription, String newStatus) {
        Task task = getTaskById(id);
        if (task != null) {
            if (newTitle != null && !newTitle.trim().isEmpty()) {
                task.setTitle(newTitle);
            }
            if (newDescription != null && !newDescription.trim().isEmpty()) {
                task.setDescription(newDescription);
            }
            if (newStatus != null && !newStatus.trim().isEmpty()) {
                task.setStatus(newStatus);
            }
            return true;
        }
        return false;
    }

    // Delete Operation
    public boolean deleteTask(int id) {
        Task task = getTaskById(id);
        if (task != null) {
            tasks.remove(task);
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
