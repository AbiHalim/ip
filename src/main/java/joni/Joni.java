package joni;

import joni.command.Command;
import joni.command.Parser;
import joni.task.TaskList;

public class Joni {
    private TaskList tasks;
    private Storage storage;

    public Joni() {
        storage = new Storage();
        tasks = new TaskList(storage.loadTasks());
    }

    public String getWelcomeMessage() {
        return "Hello! I'm Joni\nWhat can I do for you?";
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks);
        } catch (JoniException e) {
            return e.getMessage();
        }
    }
}