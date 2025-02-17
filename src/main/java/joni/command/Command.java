package joni.command;

import joni.JoniException;
import joni.task.TaskList;

/**
 * Abstract Command class that all commands inherit from.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks The TaskList instance to perform operations on.
     * @return The string representation of the command's response.
     * @throws JoniException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks) throws JoniException;

}

