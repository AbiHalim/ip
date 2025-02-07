package joni.command;

import joni.JoniException;
import joni.Storage;
import joni.task.Task;
import joni.task.TaskList;

/**
 * Handles marking or unmarking tasks.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isMarkingDone;

    /**
     * Constructs a MarkCommand with the given input parts and task type.
     *
     * @param inputParts The array of input strings containing task details.
     * @param isMarkingDone Whether marking is done or not
     */
    public MarkCommand(String[] inputParts, boolean isMarkingDone) throws JoniException {
        if (inputParts.length < 2) {
            throw new JoniException("Invalid command! Use 'mark <task number>' or 'unmark <task number>'.");
        }
        try {
            this.taskIndex = Integer.parseInt(inputParts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JoniException("Invalid task number! Use a valid number.");
        }
        this.isMarkingDone = isMarkingDone;
    }

    /**
     * Executes the mark or unmark task command.
     *
     * @param tasks The TaskList instance.
     * @return The string representation of the command's response.
     * @throws JoniException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks) throws JoniException {
        Task task = tasks.markTask(taskIndex, isMarkingDone);
        Storage.saveTasks(tasks.getTasks());

        String message = isMarkingDone
                ? "Nice! I've marked this task as done:\n   " + task
                : "OK, I've marked this task as not done yet:\n   " + task;

        return message;
    }
}
