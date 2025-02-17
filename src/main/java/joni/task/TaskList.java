package joni.task;

import java.util.ArrayList;
import java.util.Stack;

import joni.JoniException;

/**
 * Manages the task list and its operations, including undo functionality.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Stack<ArrayList<Task>> history;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.history = new Stack<>();
    }

    /**
     * Initializes a task list with existing tasks.
     *
     * @param tasks The list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.history = new Stack<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task being added should not be null!";
        saveState();
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
        saveState();
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
        saveState();
        Task task = tasks.get(index);
        task.isDone = done;
        return task;
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
            sb.append(String.format(" %d. %s%n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Undoes the most recent modification to the task list.
     *
     * @return A message indicating the undo result.
     */
    public String undo() {
        if (history.isEmpty()) {
            return "No previous actions to undo!";
        }

        tasks = history.pop();
        return "Undo successful!";
    }

    /**
     * Returns the list of tasks.
     *
     * @return The ArrayList of Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Saves the current state before making modifications.
     */
    private void saveState() {
        ArrayList<Task> taskCopy = new ArrayList<>();
        for (Task task : tasks) {
            taskCopy.add(cloneTask(task));
        }
        history.push(taskCopy);
    }

    /**
     * Creates a deep copy of a Task.
     *
     * @param task The task to copy.
     * @return A new instance of the task with the same properties.
     */
    private Task cloneTask(Task task) {
        switch (task.taskType) {
        case TODO:
            return new Todo(task.description, task.isDone);
        case DEADLINE:
            return new Deadline(task.description, ((Deadline) task).by, task.isDone);
        case EVENT:
            return new Event(task.description, ((Event) task).from, ((Event) task).to, task.isDone);
        default:
            throw new IllegalArgumentException("Unknown task type");
        }
    }
}
