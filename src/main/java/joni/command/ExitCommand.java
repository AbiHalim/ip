package joni.command;

import joni.task.TaskList;
import joni.Ui;

/**
 * The user command to exit the chatbot
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printMessage("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
