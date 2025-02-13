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

    /**
     * Saves the current list of tasks to a CSV file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        ensureFileExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.convertToCsvFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the CSV file.
     * If the file does not exist, it creates a new empty file.
     *
     * @return An ArrayList of Task objects loaded from storage.
     */
    public static ArrayList<Task> loadTasks() {
        ensureFileExists();
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromCsv(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return tasks;
    }

    private static void ensureFileExists() {
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
            System.out.println("Error creating data file: " + e.getMessage());
        }
    }

    private static Task parseTaskFromCsv(String csvLine) {
        String[] parts = csvLine.split(", ");
        if (parts.length < 3) {
            System.out.println("Corrupted data detected: " + csvLine);
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                if (parts.length < 4) {
                    return null;
                }
                LocalDate deadlineDate = LocalDate.parse(parts[3], DATE_FORMATTER);
                return new Deadline(description, deadlineDate, isDone);
            case "E":
                if (parts.length < 5) {
                    return null;
                }
                LocalDate eventFrom = LocalDate.parse(parts[3], DATE_FORMATTER);
                LocalDate eventTo = LocalDate.parse(parts[4], DATE_FORMATTER);
                return new Event(description, eventFrom, eventTo, isDone);
            default:
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error parsing task from file: " + csvLine);
            return null;
        }
    }
}
