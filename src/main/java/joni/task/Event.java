package joni.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import joni.JoniException;

/**
 * Represents an event with a start and end date
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an Event task with a start and end date.
     *
     * @param description The task description.
     * @param from The start date in "yyyy-MM-dd" format.
     * @param to The end date in "yyyy-MM-dd" format.
     * @throws JoniException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws JoniException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDate.parse(from, INPUT_FORMATTER);
            this.to = LocalDate.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JoniException("Invalid date format! Use 'yyyy-MM-dd' (e.g., 2025-02-15).");
        }
    }

    /**
     * Creates an Event task with a start and end date and completion status.
     *
     * @param description The task description.
     * @param from The start date as a LocalDate.
     * @param to The end date as a LocalDate.
     * @param isDone Whether the task is completed.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the event task to a CSV-compatible format.
     *
     * @return A string representation of the event in CSV format.
     */
    @Override
    public String convertToCsvFormat() {
        return "E, " + (isDone ? "1" : "0") + ", " + description + ", "
                + from.format(INPUT_FORMATTER) + ", " + to.format(INPUT_FORMATTER);
    }

    /**
     * Returns a formatted string representation of the event task.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: "
                + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}
