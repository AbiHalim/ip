# Joni
> Hello! My name is Joni. And this is my promise: helping you!

Joni is a comprehensive all-in-one to-do list companion to help you remember what you need to do. It's
- Text-based
- Easy to learn
- ~~FAST~~ _SUPER_ FAST to use

## Why Joni?
Joni takes on the persona of a helpful and kind companion, inspired by the character Joni from the 2005 Indonesian Film [_Janji Joni_ ](https://www.youtube.com/watch?v=Fe6-TV79Z4k) _(Joni's Promise)_. 

## What's Joni?
Joni allows you to record all the tasks, events, and deadlines you have, for example:
- [x] Finish CS2103 iP PR review
- [ ] Get dinner at Supper Stretch
- [ ] Buy laundry detergent

## Features:
1. list - Shows all tasks.
2.  todo <description> - Adds a new todo task.
3.  deadline <description> /by <date> - Adds a deadline task.
4.  event <description> /from <date> /to <date> - Adds an event.
5.  mark <task number> - Marks a task as completed.
6.  unmark <task number> - Marks a task as not done.
7.  delete <task number> - Removes a task.
8.  help - Displays this help message.
9.  find <keyword> - Finds all tasks containing <keyword>  10. bye - Exits the chatbot.

If you are interested in programming 🤓, here is how the above 'help' command is implemented:
```bash
package joni.command;

import joni.task.TaskList;
import joni.Ui;

/**
 * Displays available commands.
 */
public class HelpCommand extends Command {

    /**
     * Displays a list of available commands.
     *
     * @param tasks The TaskList instance.
     * @param ui The UI instance to display messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        String helpMessage = "Here are the available commands:\n"
                + " 1. list - Shows all tasks.\n"
                + " 2. todo <description> - Adds a new todo task.\n"
                + " 3. deadline <description> /by <date> - Adds a deadline task.\n"
                + " 4. event <description> /from <date> /to <date> - Adds an event.\n"
                + " 5. mark <task number> - Marks a task as completed.\n"
                + " 6. unmark <task number> - Marks a task as not done.\n"
                + " 7. delete <task number> - Removes a task.\n"
                + " 8. help - Displays this help message.\n"
                + " 9. find <keyword> - Finds all tasks containing <keyword> "
                + " 10. bye - Exits the chatbot.";
        ui.printMessage(helpMessage);
    }
}
```
