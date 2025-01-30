package joni.task;

import joni.JoniException;
import joni.task.Task;
import joni.task.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline
 */
public class Deadline extends Task {
    protected LocalDate by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String by) throws JoniException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JoniException("Invalid date format! Use 'yyyy-MM-dd' (e.g., 2025-02-15).");
        }
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, TaskType.DEADLINE, isDone);
        this.by = by;
    }

    @Override
    public String toCsvFormat() {
        return "D, " + (isDone ? "1" : "0") + ", " + description + ", " + by.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }
}