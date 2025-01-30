package joni;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Storage {
    private static final String DIRECTORY = "data";
    private static final String FILE_PATH = DIRECTORY + "/savedata.csv";

    /**
     * Saves the current list of tasks to savedata.csv.
     *
     * @param tasks List of tasks to be saved.
     */
    public static void saveTasks(ArrayList<Task> tasks) {
        ensureFileExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toCsvFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from savedata.csv and returns them as a list.
     *
     * @return List of tasks loaded from file.
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

    /**
     * Ensures the data directory and file exist before reading/writing.
     */
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

    /**
     * Parses a CSV line into a Task object.
     *
     * @param csvLine The CSV-formatted task entry.
     * @return Task object or null if parsing fails.
     */
    private static Task parseTaskFromCsv(String csvLine) {
        String[] parts = csvLine.split(", ");
        if (parts.length < 3) {
            System.out.println("Corrupted data detected: " + csvLine);
            return null;
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            if (parts.length < 4) return null;
            return new Deadline(description, parts[3], isDone);
        case "E":
            if (parts.length < 5) return null;
            return new Event(description, parts[3], parts[4], isDone);
        default:
            return null;
        }
    }
}