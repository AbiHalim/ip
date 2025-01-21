import java.util.Scanner;

public class Joni {
    private static final int MAX_TASKS = 100;
    private static String[] tasks = new String[MAX_TASKS];
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
        System.out.println(" And this is my promise: helping you!");
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
            } else {
                addTask(input);
            }
        }
        sc.close();
    }

    private static void addTask(String task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + task);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println(" Sorry, I can't add more tasks. The list is full!");
            System.out.println("____________________________________________________________");
        }
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (taskCount == 0) {
            System.out.println(" No tasks added yet.");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("____________________________________________________________");
    }
}