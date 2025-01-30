package joni.task;

/**
 * Represents a generic task in the task list.
 * This is an abstract class that serves as the base for specific task types like Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task with a description and task type.
     *
     * @param description The description of the task.
     * @param taskType The type of task (TODO, DEADLINE, or EVENT).
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Constructs a Task with a description, task type, and completion status.
     *
     * @param description The description of the task.
     * @param taskType The type of task (TODO, DEADLINE, or EVENT).
     * @param isDone Whether the task is completed.
     */
    public Task(String description, TaskType taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is completed, otherwise a space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Converts the task to a CSV-compatible format for storage.
     *
     * @return The CSV representation of the task.
     */
    public abstract String toCsvFormat();

    /**
     * Returns a string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}