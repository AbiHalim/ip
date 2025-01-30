package joni.command;

import joni.TaskList;
import joni.Ui;

/**
 * The user command to print the full to do list
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.printTaskList(ui);
    }
}
