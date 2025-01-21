import java.util.Scanner;

public class Joni {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

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
        while (true) {
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printTaskList();
            } else if (input.startsWith("mark ")) {
                markTaskAsDone(input);
            } else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            } else if (input.startsWith("todo ")) {
                addTodo(input.substring(5));
            } else if (input.startsWith("deadline ")) {
                addDeadline(input.substring(9));
            } else if (input.startsWith("event ")) {
                addEvent(input.substring(6));
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" Sorry, I don't understand that command.");
                System.out.println("____________________________________________________________");
            }
        }
        sc.close();
    }

    private static void addTodo(String description) {
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printTaskAdded(tasks[taskCount - 1]);
    }

    private static void addDeadline(String input) {
        String[] parts = input.split(" /by ", 2);
        if (parts.length < 2) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please provide a valid deadline with /by keyword.");
            System.out.println("____________________________________________________________");
            return;
        }
        tasks[taskCount] = new Deadline(parts[0], parts[1]);
        taskCount++;
        printTaskAdded(tasks[taskCount - 1]);
    }

    private static void addEvent(String input) {
        String[] parts = input.split(" /from | /to ", 3);
        if (parts.length < 3) {
            System.out.println("____________________________________________________________");
            System.out.println(" Please provide a valid event with /from and /to keywords.");
            System.out.println("____________________________________________________________");
            return;
        }
        tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
        taskCount++;
        printTaskAdded(tasks[taskCount - 1]);
    }

    private static void printTaskAdded(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (taskCount == 0) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + "." + tasks[i]);
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[index].markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + tasks[index]);
        System.out.println("____________________________________________________________");
    }

    private static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks[index].markAsNotDone();
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + tasks[index]);
        System.out.println("____________________________________________________________");
    }
}