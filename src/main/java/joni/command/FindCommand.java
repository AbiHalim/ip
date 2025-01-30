package joni.command;

import joni.JoniException;
import joni.task.Task;
import joni.task.TaskList;
import joni.Ui;

import java.util.ArrayList;

/**
 * Handles searching for tasks based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand to search for tasks that contain a given keyword.
     *
     * @param inputParts The user input split into parts.
     * @throws JoniException If no keyword is provided.
     */
    public FindCommand(String[] inputParts) throws JoniException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new JoniException("Please provide a keyword to search for. Usage: find <keyword>");
        }
        this.keyword = inputParts[1].trim().toLowerCase();
    }

    /**
     * Executes the find command by searching for tasks that contain the keyword.
     *
     * @param tasks The task list to search within.
     * @param ui The user interface to display results.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.printMessage("No matching tasks found.");
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append(" ").append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            ui.printMessage(result.toString());
        }
    }
}