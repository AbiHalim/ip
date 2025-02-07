package joni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import joni.command.AddCommand;
import joni.command.Command;
import joni.command.DeleteCommand;
import joni.command.ExitCommand;
import joni.command.HelpCommand;
import joni.command.ListCommand;
import joni.command.MarkCommand;
import joni.command.Parser;


/**
 * Tests for the Parser class.
 */
public class ParserTest {

    @Test
    public void parse_validTodoCommand_returnsAddCommand() throws JoniException {
        Command command = Parser.parse("todo Read a book");
        assertTrue(command instanceof AddCommand);
        assertTrue(command.toString().contains("TODO"));
    }

    @Test
    public void parse_validDeadlineCommand_returnsAddCommand() throws JoniException {
        Command command = Parser.parse("deadline Submit report /by 2025-02-20");
        assertTrue(command instanceof AddCommand);
        assertTrue(command.toString().contains("DEADLINE"));
    }

    @Test
    public void parse_validEventCommand_returnsAddCommand() throws JoniException {
        Command command = Parser.parse("event Project meeting /from 2025-03-10 /to 2025-03-12");
        assertTrue(command instanceof AddCommand);
        assertTrue(command.toString().contains("EVENT"));
    }

    @Test
    public void parse_validListCommand_returnsListCommand() throws JoniException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_validDeleteCommand_returnsDeleteCommand() throws JoniException {
        Command command = Parser.parse("delete 2");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_validMarkCommand_returnsMarkCommand() throws JoniException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_validUnmarkCommand_returnsMarkCommand() throws JoniException {
        Command command = Parser.parse("unmark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    public void parse_validHelpCommand_returnsHelpCommand() throws JoniException {
        Command command = Parser.parse("help");
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void parse_validExitCommand_returnsExitCommand() throws JoniException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_invalidCommand_throwsJoniException() {
        Exception exception = assertThrows(JoniException.class, () -> {
            Parser.parse("invalidcommand");
        });
        assertEquals("Oops! I don't understand that command.", exception.getMessage());
    }
}
