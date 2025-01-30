package joni.command;

import joni.task.Deadline;
import joni.JoniException;
import joni.Storage;
import joni.task.Task;
import joni.task.TaskList;
import joni.task.TaskType;
import joni.task.Todo;
import joni.Ui;

/**
 * Represents a user command to add a task to the to do list
 */
public class AddCommand extends Command {
    private final String[] inputParts;
    private final TaskType type;

    public AddCommand(String[] inputParts, TaskType type) {
        this.inputParts = inputParts;
        this.type = type;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks The TaskList instance to add the task to.
     * @param ui The UI instance to display messages.
     * @throws JoniException If there is an error in task creation.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws JoniException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new JoniException("Oops! The description cannot be empty.");
        }

        Task task;
        switch (type) {
        case TODO:
            task = new Todo(inputParts[1].trim());
            break;
        case DEADLINE:
            String[] deadlineParts = inputParts[1].split(" /by ", 2);
            if (deadlineParts.length < 2) throw new JoniException("Invalid deadline format.");
            task = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            break;
        default:
            throw new JoniException("Invalid task type.");
        }

        tasks.addTask(task);
        Storage.saveTasks(tasks.getTasks());
        ui.printMessage("Got it! Task added:\n   " + task);
    }
}
