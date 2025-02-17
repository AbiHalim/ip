package joni.task;

import joni.JoniException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for TaskList.
 */
public class TaskListTest {

    private TaskList taskList;

    /**
     * Sets up a new TaskList before each test.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    /**
     * Tests adding a task to the list.
     */
    @Test
    public void addTask_validTask_success() {
        Task task = new Todo("Read book");
        taskList.addTask(task);

        assertEquals(1, taskList.getTasks().size());
        assertEquals("Read book", taskList.getTasks().get(0).description);
    }

    /**
     * Tests removing a task from the list.
     */
    @Test
    public void removeTask_validIndex_success() throws JoniException {
        Task task = new Todo("Read book");
        taskList.addTask(task);
        Task removedTask = taskList.removeTask(0);

        assertEquals("Read book", removedTask.description);
        assertEquals(0, taskList.getTasks().size());
    }

    /**
     * Tests removing a task from an empty list.
     */
    @Test
    public void removeTask_emptyList_throwsException() {
        JoniException exception = assertThrows(JoniException.class, () -> taskList.removeTask(0));
        assertEquals("Invalid task number! Use a valid index.", exception.getMessage());
    }

    /**
     * Tests marking a task as done.
     */
    @Test
    public void markTask_validIndex_success() throws JoniException {
        Task task = new Todo("Complete assignment");
        taskList.addTask(task);
        taskList.markTask(0, true);

        assertTrue(taskList.getTasks().get(0).isDone);
    }

    /**
     * Tests unmarking a task as not done.
     */
    @Test
    public void unmarkTask_validIndex_success() throws JoniException {
        Task task = new Todo("Complete assignment", true);
        taskList.addTask(task);
        taskList.markTask(0, false);

        assertFalse(taskList.getTasks().get(0).isDone);
    }

    /**
     * Tests marking a task with an invalid index.
     */
    @Test
    public void markTask_invalidIndex_throwsException() {
        JoniException exception = assertThrows(JoniException.class, () -> taskList.markTask(0, true));
        assertEquals("Invalid task number! Use a valid index.", exception.getMessage());
    }

    /**
     * Tests undoing an addition.
     */
    @Test
    public void undo_addition_success() {
        taskList.addTask(new Todo("Read book"));
        taskList.undo();

        assertEquals(0, taskList.getTasks().size());
    }

    /**
     * Tests undoing a removal.
     */
    @Test
    public void undo_removal_success() throws JoniException {
        taskList.addTask(new Todo("Read book"));
        taskList.removeTask(0);
        taskList.undo();

        assertEquals(1, taskList.getTasks().size());
    }

    /**
     * Tests undo when there is nothing to undo.
     */
    @Test
    public void undo_noPreviousActions_returnsMessage() {
        assertEquals("No previous actions to undo!", taskList.undo());
    }

    /**
     * Tests printing an empty task list.
     */
    @Test
    public void printTaskList_emptyList_returnsMessage() {
        assertEquals("No tasks added yet.", taskList.printTaskList());
    }

    /**
     * Tests printing a non-empty task list.
     */
    @Test
    public void printTaskList_nonEmptyList_returnsFormattedString() {
        taskList.addTask(new Todo("Read book"));
        taskList.addTask(new Deadline("Submit report", LocalDate.of(2025, 3, 15), false));

        String expectedOutput = "Here are the tasks in your list:\n" +
                " 1. [T][ ] Read book\n" +
                " 2. [D][ ] Submit report (by: Mar 15 2025)\n";

        assertEquals(expectedOutput, taskList.printTaskList());
    }
}
