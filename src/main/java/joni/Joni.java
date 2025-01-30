package joni;

import joni.command.Command;
import joni.command.Parser;
import joni.task.TaskList;

/**
 * Main class for the chatbot.
 */
public class Joni {
    private static final TaskList TASKS = new TaskList(Storage.loadTasks());
    private static final Ui UI = new Ui();

    /**
     * Entry point for the chatbot application.
     * Initializes the chatbot and processes user input commands.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        UI.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = UI.readCommand();
                Command command = Parser.parse(input);
                command.execute(TASKS, UI);
                isRunning = !command.isExit();
            } catch (JoniException e) {
                UI.showError(e.getMessage());
            }
        }
    }
}