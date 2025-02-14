package joni.command;


import joni.JoniException;
import joni.task.TaskType;

/**
 * Parses user input into Commands.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding command.
     *
     * @param userInput The raw input entered by the user.
     * @return A Command object representing the user's request.
     * @throws JoniException If the input command is not recognized.
     */
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
        case UNDO:
            return new UndoCommand(); // New Undo command
        case BYE:
            return new ExitCommand();
        default:
            throw new JoniException("Oops! I don't understand that command.");
        }
    }
}
