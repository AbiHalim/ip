package joni;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event with a start and end date
 */
class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Event(String description, String from, String to) throws JoniException {
        super(description, TaskType.EVENT);
        try {
            this.from = LocalDate.parse(from, INPUT_FORMATTER);
            this.to = LocalDate.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JoniException("Invalid date format! Use 'yyyy-MM-dd' (e.g., 2025-02-15).");
        }
    }

    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toCsvFormat() {
        return "E, " + (isDone ? "1" : "0") + ", " + description + ", " + from.format(INPUT_FORMATTER) + ", " + to.format(INPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }
}