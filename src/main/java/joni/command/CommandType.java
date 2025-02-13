package joni.command;

/**
 * Represents the different types of commands that can be executed by the chatbot.
 */
public enum CommandType {
    /**
     * Exits the chatbot.
     */
    BYE,

    /**
     * Lists all tasks stored in the chatbot.
     */
    LIST,

    /**
     * Marks a task as completed.
     */
    MARK,

    /**
     * Unmarks a task as not completed.
     */
    UNMARK,

    /**
     * Adds a new to-do task.
     */
    TODO,

    /**
     * Adds a new deadline task.
     */
    DEADLINE,

    /**
     * Adds a new event task.
     */
    EVENT,

    /**
     * Deletes a task from the list.
     */
    DELETE,

    /**
     * Represents an unrecognized command.
     */
    UNKNOWN,

    /**
     * Displays a list of available commands.
     */
    HELP,

    /**
     * Searches for tasks that contain a given keyword.
     */
    FIND;

    /**
     * Converts a user input string into a corresponding CommandType.
     *
     * @param command The user input string.
     * @return The corresponding CommandType, or UNKNOWN if the input does not match any known command.
     */
    public static CommandType fromString(String command) {
        switch (command.toLowerCase()) {
        case "help": return HELP;
        case "bye": return BYE;
        case "exit": return BYE;
        case "list": return LIST;
        case "mark": return MARK;
        case "unmark": return UNMARK;
        case "todo": return TODO;
        case "deadline": return DEADLINE;
        case "event": return EVENT;
        case "delete": return DELETE;
        case "find": return FIND;
        default: return UNKNOWN;
        }
    }
}