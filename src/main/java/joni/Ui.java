package joni;

import java.util.Scanner;

/**
 * Contains the user interface for the chatbot
 */
public class Ui {
    private final Scanner sc;
    private static final String DIVIDER = "____________________________________________________________";

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the welcome message when the chatbot starts.
     */
    public void showWelcome() {
        System.out.println(DIVIDER);
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
        System.out.println(" Hello! My name is Joni");
        System.out.println(" And this is my promise: helping you!");
        System.out.println(" Type \"help\" for a list of commands.");
        System.out.println(DIVIDER);
    }

    /**
     * Reads the command input by the user
     *
     * @return User's input as a trimmed string
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Displays a divider line to separate outputs.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println(" Error: " + message);
        System.out.println(DIVIDER);
    }

    /**
     * Displays a warning message if previous tasks cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println(" Warning: Could not load previous tasks. Starting fresh!");
    }

    /**
     * Displays a given message within dividers.
     *
     * @param message The message to be displayed.
     */
    public void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}