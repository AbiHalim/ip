package joni.command;

import joni.JoniException;
import joni.TaskList;
import joni.Ui;

/**
 * Abstract Command class that all commands inherit from.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui) throws JoniException;
    public boolean isExit() {
        return false;
    }
}

