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

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println(" Error: " + message);
        System.out.println(DIVIDER);
    }

    public void showLoadingError() {
        System.out.println(" Warning: Could not load previous tasks. Starting fresh!");
    }

    public void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }
}