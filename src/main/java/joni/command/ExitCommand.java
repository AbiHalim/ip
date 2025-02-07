package joni.command;

import joni.Ui;
import joni.task.TaskList;

/**
 * The user command to exit the chatbot
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command and prints the farewell message.
     *
     * @param tasks The TaskList instance.
     * @param ui The UI instance to display messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Determines if this command is an exit command.
     *
     * @return True, indicating the chatbot should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
