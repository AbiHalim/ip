package joni.command;

import java.util.ArrayList;

import joni.JoniException;
import joni.task.Task;
import joni.task.TaskList;

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
     * @return The string representation of the command's response.
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks.getTasks()) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append(String.format(" %d. %s%n", i + 1, matchingTasks.get(i)));
            }
            return result.toString();
        }
    }
}
