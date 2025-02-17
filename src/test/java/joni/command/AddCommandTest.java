package joni.command;

import joni.JoniException;
import joni.task.TaskList;
import joni.task.TaskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * JUnit tests for AddCommand, particularly the execute method.
 */
public class AddCommandTest {

    private TaskList taskList;

    /**
     * Sets up a new TaskList before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests adding a ToDo task.
     */
    @Test
    public void execute_addTodo_success() throws JoniException {
        String[] inputParts = {"todo", "Read book"};
        AddCommand command = new AddCommand(inputParts, TaskType.TODO);
        String result = command.execute(taskList);

        assertEquals("Got it! Task added:\n   [T][ ] Read book", result);
        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests adding a Deadline task.
     */
    @Test
    public void execute_addDeadline_success() throws JoniException {
        String[] inputParts = {"deadline", "Submit report /by 2025-03-15"};
        AddCommand command = new AddCommand(inputParts, TaskType.DEADLINE);
        String result = command.execute(taskList);

        assertEquals("Got it! Task added:\n   [D][ ] Submit report (by: Mar 15 2025)", result);
        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests adding an Event task.
     */
    @Test
    public void execute_addEvent_success() throws JoniException {
        String[] inputParts = {"event", "Project meeting /from 2025-03-10 /to 2025-03-11"};
        AddCommand command = new AddCommand(inputParts, TaskType.EVENT);
        String result = command.execute(taskList);

        assertEquals("Got it! Task added:\n   [E][ ] Project meeting (from: Mar 10 2025 to: Mar 11 2025)", result);
        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests adding a task with an empty description, which should throw an exception.
     */
    @Test
    public void execute_emptyDescription_throwsException() {
        String[] inputParts = {"todo", ""};
        AddCommand command = new AddCommand(inputParts, TaskType.TODO);

        JoniException exception = assertThrows(JoniException.class, () -> command.execute(taskList));
        assertEquals("Oops! The description cannot be empty.", exception.getMessage());
    }

    /**
     * Tests adding a deadline task with an invalid format, which should throw an exception.
     */
    @Test
    public void execute_invalidDeadlineFormat_throwsException() {
        String[] inputParts = {"deadline", "Submit report"};
        AddCommand command = new AddCommand(inputParts, TaskType.DEADLINE);

        JoniException exception = assertThrows(JoniException.class, () -> command.execute(taskList));
        assertEquals("Invalid deadline format. Use: deadline <description> /by <yyyy-MM-dd>", exception.getMessage());
    }

    /**
     * Tests adding an event task with an invalid format, which should throw an exception.
     */
    @Test
    public void execute_invalidEventFormat_throwsException() {
        String[] inputParts = {"event", "Team meeting /from 2025-03-10"};
        AddCommand command = new AddCommand(inputParts, TaskType.EVENT);

        JoniException exception = assertThrows(JoniException.class, () -> command.execute(taskList));
        assertEquals("Invalid event format. Use: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>", exception.getMessage());
    }
}
