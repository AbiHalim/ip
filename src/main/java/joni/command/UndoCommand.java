package joni.command;

import joni.task.TaskList;

/**
 * Handles undoing the most recent command.
 */
public class UndoCommand extends Command {

    /**
     * Executes the undo command.
     *
     * @param tasks The TaskList instance to restore the previous state.
     * @return A message indicating whether undo was successful.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.undo();
    }
}
