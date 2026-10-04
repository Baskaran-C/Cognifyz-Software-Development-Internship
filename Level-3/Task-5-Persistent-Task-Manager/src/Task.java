public class Task {
    private int id;
    private String title;
    private String description;
    private String status;

    public Task(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Convert task object to plain text pipe-delimited format for storage
    public String toFileFormat() {
        return id + "|" + title.replace("|", "-") + "|" + description.replace("|", "-") + "|" + status;
    }

    // Parse plain text line into Task object
    public static Task fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        if (parts.length >= 4) {
            try {
                int id = Integer.parseInt(parts[0]);
                return new Task(id, parts[1], parts[2], parts[3]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Title: %-25s | Status: %-12s | Desc: %s",
                id, title, status, description);
    }
}
