package joni;

enum CommandType {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN, HELP, FIND;

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