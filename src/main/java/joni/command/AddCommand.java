package joni.command;

import joni.JoniException;
import joni.Storage;
import joni.task.Deadline;
import joni.task.Event;
import joni.task.Task;
import joni.task.TaskList;
import joni.task.TaskType;
import joni.task.Todo;

/**
 * Represents a user command to add a task to the to-do list.
 */
public class AddCommand extends Command {
    private final String[] inputParts;
    private final TaskType type;

    /**
     * Constructs an AddCommand with the given input parts and task type.
     *
     * @param inputParts The array of input strings containing task details.
     * @param type The type of task to be added.
     */
    public AddCommand(String[] inputParts, TaskType type) {
        this.inputParts = inputParts;
        this.type = type;
    }

    /**
     * Executes the add task command.
     *
     * @param tasks The TaskList instance to add the task to.
     * @return The string representation of the command's response.
     * @throws JoniException If there is an error in task creation.
     */
    @Override
    public String execute(TaskList tasks) throws JoniException {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new JoniException("Oops! The description cannot be empty.");
        }

        Task task;
        switch (type) {
        case TODO:
            task = new Todo(inputParts[1].trim());
            break;
        case DEADLINE:
            task = parseDeadline(inputParts[1]);
            break;
        case EVENT:
            task = parseEvent(inputParts[1]);
            break;
        default:
            throw new JoniException("Invalid task type.");
        }

        tasks.addTask(task);
        Storage.saveTasks(tasks.getTasks());
        return "Got it! Task added:\n   " + task;
    }

    /**
     * Parses a deadline task from user input.
     *
     * @param input The user input string.
     * @return A Deadline task.
     * @throws JoniException If the format is incorrect.
     */
    private Task parseDeadline(String input) throws JoniException {
        String[] deadlineParts = input.split(" /by ", 2);
        if (deadlineParts.length < 2) {
            throw new JoniException("Invalid deadline format. Use: deadline <description> /by <date>");
        }
        return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
    }

    /**
     * Parses an event task from user input.
     *
     * @param input The user input string.
     * @return An Event task.
     * @throws JoniException If the format is incorrect.
     */
    private Task parseEvent(String input) throws JoniException {
        String[] eventParts = input.split(" /from | /to ", 3);
        if (eventParts.length < 3) {
            throw new JoniException("Invalid event format. Use: event <description> /from <start date> /to <end date>");
        }
        return new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
    }
}
