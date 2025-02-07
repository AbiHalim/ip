package joni;

import joni.command.Command;
import joni.command.Parser;
import joni.task.TaskList;

/**
 * Main class for the chatbot.
 */
public class Joni {
    private TaskList tasks;
    private Storage storage;

    /**
     * Initializes a Joni instance with storage and task list
     */
    public Joni() {
        storage = new Storage();
        tasks = new TaskList(storage.loadTasks());
    }

    /**
     * Returns the welcome message for the Joni chatbot.
     *
     * @return A string containing the welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! My name is Joni\n And this is my promise: helping you!\n"
                + "Type \"help\" for a list of commands.";
    }

    /**
     * Processes the user input, executes the corresponding command,
     * and returns the response from Joni.
     *
     * @param input The user input string to be processed.
     * @return A string representing Joni's response to the given input.
     * @throws JoniException If there is an issue processing the input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks);
        } catch (JoniException e) {
            return e.getMessage();
        }
    }
}
