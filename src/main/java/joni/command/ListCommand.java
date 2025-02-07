package joni.command;

import joni.task.TaskList;

/**
 * The user command to print the full to do list
 */
public class ListCommand extends Command {

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks The TaskList instance.
     * @return The string representation of the command's response.
     */
    @Override
    public String execute(TaskList tasks) {
        return tasks.printTaskList();
    }
}
