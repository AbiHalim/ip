package joni.task;

/**
 * Represents the different possible types of tasks.
 */
public enum TaskType {
    /**
     * A task without a specific deadline.
     */
    TODO,

    /**
     * A task with a specific deadline.
     */
    DEADLINE,

    /**
     * A task with a start and end date.
     */
    EVENT;
}
