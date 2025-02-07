package joni.command;

import joni.JoniException;
import joni.Storage;
import joni.task.Task;
import joni.task.TaskList;

/**
 * Handles deleting tasks from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the given input parts
     *
     * @param inputParts The array of input strings containing task details.
     */
    public DeleteCommand(String[] inputParts) throws JoniException {
        if (inputParts.length < 2) {
            throw new JoniException("Invalid command! Use 'delete <task number>'.");
        }
        try {
            this.taskIndex = Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JoniException("Invalid task number! Use a valid number.");
        }
    }

    /**
     * Executes the delete task command.
     *
     * @param tasks The TaskList instance to remove the task from.
     * @return The string representation of the command's response.
     * @throws JoniException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws JoniException {
        Task removedTask = tasks.removeTask(taskIndex);
        Storage.saveTasks(tasks.getTasks());

        return "Noted. I've removed this task:\n   " + removedTask
                + "\nNow you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}
