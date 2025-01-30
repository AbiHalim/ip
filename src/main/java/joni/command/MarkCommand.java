package joni.command;

import joni.JoniException;
import joni.Storage;
import joni.Task;
import joni.TaskList;
import joni.Ui;

/**
 * Handles marking or unmarking tasks.
 */
public class MarkCommand extends Command {
    private final int taskIndex;
    private final boolean isMarkingDone;

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

    @Override
    public void execute(TaskList tasks, Ui ui) throws JoniException {
        Task task = tasks.markTask(taskIndex, isMarkingDone);
        Storage.saveTasks(tasks.getTasks());

        String message = isMarkingDone
                ? "Nice! I've marked this task as done:\n   " + task
                : "OK, I've marked this task as not done yet:\n   " + task;

        ui.printMessage(message);
    }
}