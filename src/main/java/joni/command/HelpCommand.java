package joni.command;

import joni.task.TaskList;
import joni.Ui;

/**
 * Displays available commands.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        String helpMessage = "Here are the available commands:\n"
                + " 1. list - Shows all tasks.\n"
                + " 2. todo <description> - Adds a new todo task.\n"
                + " 3. deadline <description> /by <date> - Adds a deadline task.\n"
                + " 4. event <description> /from <date> /to <date> - Adds an event.\n"
                + " 5. mark <task number> - Marks a task as completed.\n"
                + " 6. unmark <task number> - Marks a task as not done.\n"
                + " 7. delete <task number> - Removes a task.\n"
                + " 8. help - Displays this help message.\n"
                + " 9. bye - Exits the chatbot.";
        ui.printMessage(helpMessage);
    }
}