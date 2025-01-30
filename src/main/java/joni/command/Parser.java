package joni.command;

import joni.command.CommandType;
import joni.JoniException;
import joni.command.Command;
import joni.command.AddCommand;
import joni.command.ExitCommand;
import joni.command.ListCommand;
import joni.command.DeleteCommand;
import joni.command.MarkCommand;
import joni.command.HelpCommand;
import joni.command.FindCommand;
import joni.task.TaskType;

/**
 * Parses user input into Commands.
 */
public class Parser {
    public static Command parse(String userInput) throws JoniException {
        String[] inputParts = userInput.split(" ", 2);
        CommandType command = CommandType.fromString(inputParts[0]);

        switch (command) {
        case LIST:
            return new ListCommand();
        case TODO:
            return new AddCommand(inputParts, TaskType.TODO);
        case DEADLINE:
            return new AddCommand(inputParts, TaskType.DEADLINE);
        case EVENT:
            return new AddCommand(inputParts, TaskType.EVENT);
        case DELETE:
            return new DeleteCommand(inputParts);
        case MARK:
            return new MarkCommand(inputParts, true);
        case UNMARK:
            return new MarkCommand(inputParts, false);
        case HELP:
            return new HelpCommand();
        case FIND:
            return new FindCommand(inputParts);
        case BYE:
            return new ExitCommand();
        default:
            throw new JoniException("Oops! I don't understand that command.");
        }
    }
}