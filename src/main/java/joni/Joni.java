package joni;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for the chatbot.
 */
public class Joni {
    private static ArrayList<Task> tasks = Storage.loadTasks();
    private static final String DIVIDER = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = "    .---.    .-'''-.                    \n" +
                "    |   |   '   _    \\                  \n" +
                "    '---' /   /` '.   \\    _..._   .--. \n" +
                "    .---..   |     \\  '  .'     '. |__| \n" +
                "    |   ||   '      |  '.   .-.   ..--. \n" +
                "    |   |\\    \\     / / |  '   '  ||  | \n" +
                "    |   | `.   ` ..' /  |  |   |  ||  | \n" +
                "    |   |    '-...-'`   |  |   |  ||  | \n" +
                "    |   |               |  |   |  ||  | \n" +
                "    |   |               |  |   |  ||__| \n" +
                " __.'   '               |  |   |  |     \n" +
                "|      '                |  |   |  |     \n" +
                "|____.'                 '--'   '--'     \n";
        System.out.println(logo);

        System.out.println(DIVIDER);
        System.out.println(" Hello! My name is Joni");
        System.out.println(" What can I do for you?");
        System.out.println(DIVIDER);

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            try {
                String input = sc.nextLine().trim();
                isRunning = processCommand(input);
            } catch (JoniException e) {
                printErrorMessage(e.getMessage());
            } catch (Exception e) {
                printErrorMessage("Unexpected error occurred. Please try again.");
            }
        }
        sc.close();
    }

    private static boolean processCommand(String input) throws JoniException {
        String[] inputParts = input.split(" ", 2);
        CommandType command = CommandType.fromString(inputParts[0]);

        switch (command) {
        case HELP:
            System.out.println(DIVIDER);
            System.out.println(" Here are the available commands:");
            System.out.println(" 1. list - Shows all tasks.");
            System.out.println(" 2. todo <description> - Adds a new todo task.");
            System.out.println(" 3. deadline <description> /by <date/time> - Adds a deadline task.");
            System.out.println(" 4. event <description> /from <start> /to <end> - Adds an event.");
            System.out.println(" 5. mark <task number> - Marks a task as completed.");
            System.out.println(" 6. unmark <task number> - Marks a task as not done.");
            System.out.println(" 7. delete <task number> - Removes a task.");
            System.out.println(" 8. help - Displays this help message.");
            System.out.println(" 9. bye - Exits the chatbot.");
            System.out.println(DIVIDER);
        case BYE:
            System.out.println(DIVIDER);
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println(DIVIDER);
            return false;
        case LIST:
            printTaskList();
            break;
        case MARK:
            markTaskAsDone(inputParts);
            break;
        case UNMARK:
            unmarkTask(inputParts);
            break;
        case TODO:
            if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                throw new JoniException("Oops! The description of a todo cannot be empty.\n"
                        + "Tip: Try 'todo <task description>'.");
            }
            addTodo(inputParts[1].trim());
            break;
        case DEADLINE:
            addDeadline(inputParts[1].trim());
            break;
        case EVENT:
            addEvent(inputParts[1].trim());
            break;
        case DELETE:
            deleteTask(inputParts);
            break;
        default:
            throw new JoniException("Oops! I don't understand that command.\n"
                    + "Tip: Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', or 'delete'.");
        }
        return true;
    }

    private static void addTodo(String description) {
        tasks.add(new Todo(description));
        Storage.saveTasks(tasks);
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void addDeadline(String input) throws JoniException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2) {
            throw new JoniException("Oops! A deadline requires '/by' followed by a date/time.\n"
                    + "Tip: Try 'deadline <task description> /by <due date>'.");
        }
        tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
        Storage.saveTasks(tasks);
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void addEvent(String input) throws JoniException {
        String[] parts = input.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new JoniException("Oops! An event requires '/from' and '/to' followed by dates/times.\n"
                    + "Tip: Try 'event <description> /from <start time> /to <end time>'.");
        }
        tasks.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        Storage.saveTasks(tasks);
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void markTaskAsDone(String[] inputParts) throws JoniException {
        if (inputParts.length < 2) {
            throw new JoniException("Invalid command! Use 'mark <task number>'.");
        }
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            tasks.get(index).markAsDone();
            Storage.saveTasks(tasks);
            System.out.println(DIVIDER);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(index));
            System.out.println(DIVIDER);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JoniException("Invalid task number! Use 'mark <task number>'.");
        }
    }

    private static void deleteTask(String[] inputParts) throws JoniException {
        if (inputParts.length < 2) {
            throw new JoniException("Invalid command! Use 'delete <task number>'.");
        }
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new JoniException("Invalid task number! Use 'delete <task number>'.");
            }
            Task removedTask = tasks.remove(index);
            Storage.saveTasks(tasks);
            System.out.println(DIVIDER);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(DIVIDER);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JoniException("Invalid task number! Use 'delete <task number>'.");
        }
    }

    private static void printTaskAdded(Task task) {
        System.out.println(DIVIDER);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    private static void printTaskList() {
        System.out.println(DIVIDER);
        if (tasks.isEmpty()) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(DIVIDER);
    }

    private static void unmarkTask(String[] inputParts) throws JoniException {
        if (inputParts.length < 2) {
            throw new JoniException("Invalid command! Use 'unmark <task number>'.");
        }
        try {
            int index = Integer.parseInt(inputParts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new JoniException("Invalid task number! Use 'unmark <task number>'.");
            }
            tasks.get(index).markAsNotDone();
            Storage.saveTasks(tasks);
            System.out.println(DIVIDER);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(index));
            System.out.println(DIVIDER);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JoniException("Invalid task number! Use 'unmark <task number>'.");
        }
    }

    private static void printErrorMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(" " + message);
        System.out.println(DIVIDER);
    }
}