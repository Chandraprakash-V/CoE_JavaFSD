package Tasks1;
import java.util.*;

class Task {
    private String id;
    private String description;
    private int priority;

    public Task(String id, String description, int priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Task[ID=" + id + ", Description=" + description + ", Priority=" + priority + "]";
    }
}

class TaskManager {
    private PriorityQueue<Task> taskQueue;
    private Map<String, Task> taskMap;

    public TaskManager() {
        taskQueue = new PriorityQueue<>(Comparator.comparingInt(Task::getPriority).reversed());
        taskMap = new HashMap<>();
    }

    public void addTask(String id, String description, int priority) {
        if (taskMap.containsKey(id)) {
            System.out.println("Task with ID " + id + " already exists.");
            return;
        }
        Task newTask = new Task(id, description, priority);
        taskQueue.add(newTask);
        taskMap.put(id, newTask);
    }

    public void removeTask(String id) {
        Task task = taskMap.remove(id);
        if (task != null) {
            taskQueue.remove(task);
            System.out.println("Task with ID " + id + " removed.");
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    public Task getHighestPriorityTask() {
        return taskQueue.peek();
    }

    public void displayTasks() {
        System.out.println("Current Tasks in Priority Order:");
        for (Task task : taskQueue) {
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        manager.addTask("1", "Fix bug in login", 5);
        manager.addTask("2", "Implement feature X", 8);
        manager.addTask("3", "Write unit tests", 3);

        System.out.println("Highest Priority Task: " + manager.getHighestPriorityTask());

        manager.removeTask("1");
        
        manager.displayTasks();
    }
}
