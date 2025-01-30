package joni;

class Todo extends Task {
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    @Override
    public String toCsvFormat() {
        return "T, " + (isDone ? "1" : "0") + ", " + description;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}