package joni;

import joni.command.Command;
import joni.task.TaskList;

/**
 * Main class for the chatbot.
 */
public class Joni {
    private static final TaskList tasks = new TaskList(Storage.loadTasks());
    private static final Ui ui = new Ui();

    /**
     * Entry point for the chatbot application.
     * Initializes the chatbot and processes user input commands.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ui.showWelcome();
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(tasks, ui);
                isRunning = !command.isExit();
            } catch (JoniException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}