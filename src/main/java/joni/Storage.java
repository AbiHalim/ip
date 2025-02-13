package joni;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import joni.task.Deadline;
import joni.task.Event;
import joni.task.Task;
import joni.task.Todo;

/**
 * Handles loading and saving of tasks to a CSV file.
 */
public class Storage {
    private static final String DIRECTORY = "data";
    private static final String FILE_PATH = DIRECTORY + "/savedata.csv";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final Logger LOGGER = Logger.getLogger(Storage.class.getName());

    /**
     * Saves the current list of tasks to a CSV file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null when saving!";
        ensureFileExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.convertToCsvFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error saving tasks", e);
        }
    }

    /**
     * Loads tasks from the CSV file. If the file does not exist, it creates a new empty file.
     *
     * @return An ArrayList of Task objects loaded from storage.
     */
    public ArrayList<Task> loadTasks() {
        ensureFileExists();
        assert Files.exists(Paths.get(FILE_PATH)) : "Task data file should exist after ensuring existence!";
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseTaskFromCsv(line).ifPresent(tasks::add);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading tasks", e);
        }

        return tasks;
    }

    /**
     * Ensures that the required file and directory exist.
     */
    private void ensureFileExists() {
        try {
            Path directoryPath = Paths.get(DIRECTORY);
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Path filePath = Paths.get(FILE_PATH);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error creating data file", e);
        }
    }

    /**
     * Parses a CSV line into a Task object.
     *
     * @param csvLine The CSV line containing task details.
     * @return An Optional<Task> containing the parsed task, or empty if parsing fails.
     */
    private Optional<Task> parseTaskFromCsv(String csvLine) {
        String[] parts = csvLine.split(", ");
        if (parts.length < 3) {
            LOGGER.warning("Corrupted data detected: " + csvLine);
            return Optional.empty();
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
            case "T":
                return Optional.of(new Todo(description, isDone));
            case "D":
                return parseDeadlineTask(parts, description, isDone);
            case "E":
                return parseEventTask(parts, description, isDone);
            default:
                LOGGER.warning("Unknown task type: " + type);
                return Optional.empty();
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error parsing task from file: " + csvLine, e);
            return Optional.empty();
        }
    }

    /**
     * Parses a deadline task from CSV parts.
     *
     * @param parts The split CSV line.
     * @param description The task description.
     * @param isDone Whether the task is marked as done.
     * @return An Optional containing the Deadline task or empty if parsing fails.
     */
    private Optional<Task> parseDeadlineTask(String[] parts, String description, boolean isDone) {
        if (parts.length < 4) {
            LOGGER.warning("Incomplete deadline task: " + String.join(", ", parts));
            return Optional.empty();
        }
        LocalDate deadlineDate = LocalDate.parse(parts[3], DATE_FORMATTER);
        return Optional.of(new Deadline(description, deadlineDate, isDone));
    }

    /**
     * Parses an event task from CSV parts.
     *
     * @param parts The split CSV line.
     * @param description The task description.
     * @param isDone Whether the task is marked as done.
     * @return An Optional containing the Event task or empty if parsing fails.
     */
    private Optional<Task> parseEventTask(String[] parts, String description, boolean isDone) {
        if (parts.length < 5) {
            LOGGER.warning("Incomplete event task: " + String.join(", ", parts));
            return Optional.empty();
        }
        LocalDate eventFrom = LocalDate.parse(parts[3], DATE_FORMATTER);
        LocalDate eventTo = LocalDate.parse(parts[4], DATE_FORMATTER);
        return Optional.of(new Event(description, eventFrom, eventTo, isDone));
    }
}
