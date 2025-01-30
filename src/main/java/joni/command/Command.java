package joni.command;

import joni.JoniException;
import joni.task.TaskList;
import joni.Ui;

/**
 * Abstract Command class that all commands inherit from.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The TaskList instance to perform operations on.
     * @param ui The UI instance for user interaction.
     * @throws JoniException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws JoniException;

    /**
     * Determines if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

