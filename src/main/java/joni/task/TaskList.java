package joni.task;

import java.util.ArrayList;

import joni.JoniException;

/**
 * Manages the task list and its operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a task list with existing tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list based on the given index.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task object.
     * @throws JoniException If the index is out of bounds.
     */
    public Task removeTask(int index) throws JoniException {
        if (index < 0 || index >= tasks.size()) {
            throw new JoniException("Invalid task number! Use a valid index.");
        }
        return tasks.remove(index);
    }

    /**
     * Marks or unmarks a task in the task list.
     *
     * @param index The index of the task to mark or unmark.
     * @param done True to mark as done, false to mark as not done.
     * @return The updated Task object.
     * @throws JoniException If the index is out of bounds.
     */
    public Task markTask(int index, boolean done) throws JoniException {
        if (index < 0 || index >= tasks.size()) {
            throw new JoniException("Invalid task number! Use a valid index.");
        }
        if (done) {
            tasks.get(index).markAsDone();
        } else {
            tasks.get(index).markAsNotDone();
        }
        return tasks.get(index);
    }

    /**
     * Prints all tasks in the task list.
     */
    public String printTaskList() {
        if (tasks.isEmpty()) {
            return "No tasks added yet.";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
