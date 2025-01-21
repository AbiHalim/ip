import java.util.ArrayList;
import java.util.Scanner;

public class Joni {
    private static ArrayList<Task> tasks = new ArrayList<>();

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

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! My name is Joni");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

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
        if (input.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            return false;
        } else if (input.equalsIgnoreCase("list")) {
            printTaskList();
        } else if (input.startsWith("mark ")) {
            markTaskAsDone(input);
        } else if (input.startsWith("unmark ")) {
            unmarkTask(input);
        } else if (input.startsWith("todo ")) {
            if (input.length() <= 5) {
                throw new JoniException("Oops! The description of a todo cannot be empty. \n"
                        + "Tip: Try 'todo <task description>'.");
            }
            addTodo(input.substring(5).trim());
        } else if (input.startsWith("deadline ")) {
            addDeadline(input.substring(9).trim());
        } else if (input.startsWith("event ")) {
            addEvent(input.substring(6).trim());
        } else if (input.startsWith("delete ")) {
            deleteTask(input);
        } else {
            throw new JoniException("Oops! I don't understand that command. \n"
                    + "Tip: Try 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', or 'delete'.");
        }
        return true;
    }

    private static void addTodo(String description) {
        tasks.add(new Todo(description));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void addDeadline(String input) throws JoniException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2) {
            throw new JoniException("Oops! A deadline requires '/by' followed by a date/time.\n"
                    + "Tip: Try 'deadline <task description> /by <due date>'.");
        }
        tasks.add(new Deadline(parts[0].trim(), parts[1].trim()));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void addEvent(String input) throws JoniException {
        String[] parts = input.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new JoniException("Oops! An event requires '/from' and '/to' followed by dates/times.\n"
                    + "Tip: Try 'event <description> /from <start time> /to <end time>'.");
        }
        tasks.add(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
        printTaskAdded(tasks.get(tasks.size() - 1));
    }

    private static void deleteTask(String input) throws JoniException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new JoniException("Invalid task number! Use 'delete <task number>'.");
            }
            Task removedTask = tasks.remove(index);
            System.out.println("____________________________________________________________");
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JoniException("Invalid task number! Use 'delete <task number>'.");
        }
    }

    private static void printTaskAdded(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String input) throws JoniException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(index).markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(index));
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JoniException("Invalid task number! Use 'mark <task number>'.");
        }
    }

    private static void unmarkTask(String input) throws JoniException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            tasks.get(index).markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(index));
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new JoniException("Invalid task number! Use 'unmark <task number>'.");
        }
    }

    private static void printErrorMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + message);
        System.out.println("____________________________________________________________");
    }
}