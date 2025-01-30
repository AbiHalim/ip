package joni.task;

import joni.JoniException;
import joni.Ui;
import joni.task.Task;

import java.util.ArrayList;

/**
 * Manages the task list and its operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) throws JoniException {
        if (index < 0 || index >= tasks.size()) {
            throw new JoniException("Invalid task number! Use a valid index.");
        }
        return tasks.remove(index);
    }

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

    public void printTaskList(Ui ui) {
        if (tasks.isEmpty()) {
            ui.printMessage("No tasks added yet.");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        ui.printMessage(sb.toString());
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}