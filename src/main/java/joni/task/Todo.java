package joni.task;

import joni.task.Task;
import joni.task.TaskType;

/**
 * Represents a basic to-do task without any time constraints.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with a description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Creates a Todo task with a description and completion status.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    /**
     * Converts the Todo task to a CSV-compatible format for storage.
     *
     * @return The CSV representation of the Todo task.
     */
    @Override
    public String toCsvFormat() {
        return "T, " + (isDone ? "1" : "0") + ", " + description;
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return The formatted string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}