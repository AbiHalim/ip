package joni.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import joni.JoniException;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate by;

    /**
     * Creates a Deadline task with a due date.
     *
     * @param description The task description.
     * @param by The deadline date in "yyyy-MM-dd" format.
     * @throws JoniException If the date format is invalid.
     */
    public Deadline(String description, String by) throws JoniException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JoniException("Invalid date format! Use 'yyyy-MM-dd' (e.g., 2025-02-15).");
        }
    }

    /**
     * Creates a Deadline task with a due date and completion status.
     *
     * @param description The task description.
     * @param by The deadline date as a LocalDate.
     * @param isDone Whether the task is completed.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    /**
     * Converts the deadline task to a CSV-compatible format.
     *
     * @return A string representation of the task in CSV format.
     */
    @Override
    public String toCsvFormat() {
        return "D, " + (isDone ? "1" : "0") + ", " + description + ", " + by.format(INPUT_FORMATTER);
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}
