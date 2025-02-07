package joni;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import joni.command.Command;
import joni.command.Parser;
import joni.task.TaskList;

/**
 * Tests for the Joni chatbot.
 */
public class JoniTest {
    private TaskList taskList;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        taskList = new TaskList();
    }

    @Test
    public void testValidTodoCommand() {
        try {
            Command command = Parser.parse("todo Read a book");
            command.execute(taskList, ui);
            assertEquals(1, taskList.getTasks().size());
            assertTrue(taskList.getTasks().get(0).toString().contains("Read a book"));
        } catch (JoniException e) {
            fail("Exception should not be thrown for a valid todo command.");
        }
    }

    @Test
    public void testInvalidTodoCommand() {
        Exception exception = assertThrows(JoniException.class, () -> {
            Command command = Parser.parse("todo");
            command.execute(taskList, ui);
        });
        assertEquals("Oops! The description cannot be empty.", exception.getMessage());
    }

    @Test
    public void testValidDeadlineCommand() {
        try {
            Command command = Parser.parse("deadline Submit report /by 2025-02-20");
            command.execute(taskList, ui);
            assertEquals(1, taskList.getTasks().size());
            assertTrue(taskList.getTasks().get(0).toString().contains("Submit report"));
        } catch (JoniException e) {
            fail("Exception should not be thrown for a valid deadline command.");
        }
    }

    @Test
    public void testInvalidDeadlineCommand() {
        Exception exception = assertThrows(JoniException.class, () -> {
            Command command = Parser.parse("deadline Submit report /by invalid-date");
            command.execute(taskList, ui);
        });
        assertEquals("Invalid date format! Use 'yyyy-MM-dd' (e.g., 2025-02-15).", exception.getMessage());
    }

    @Test
    public void testExitCommand() {
        try {
            Command command = Parser.parse("bye");
            assertTrue(command.isExit());
        } catch (JoniException e) {
            fail("Exception should not be thrown for a valid bye command.");
        }
    }

    @Test
    public void testInvalidCommand() {
        Exception exception = assertThrows(JoniException.class, () -> {
            Parser.parse("invalidcommand");
        });
        assertEquals("Oops! I don't understand that command.", exception.getMessage());
    }
}
