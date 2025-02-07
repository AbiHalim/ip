package joni.command;

import joni.Ui;
import joni.task.TaskList;

/**
 * The user command to print the full to do list
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList instance.
     * @param ui The UI instance to display messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.printTaskList(ui);
    }
}
