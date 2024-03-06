import Exceptions.ArgumentNotFoundException;
import Exceptions.TaskNotFoundException;
import Tasks.Task;

import java.util.ArrayList;

/**
 * The Parser class provides methods for parsing user input related to task commands.
 */
public class Parser {
    public static final int MARK_TASK_INDEX = 5;

    public static final int UNMARK_TASK_INDEX = 7;

    public static final int EVENT_TASK_INDEX = 6;

    public static final int DEADLINE_TASK_INDEX = 9;

    public static final int TODO_TASK_INDEX = 5;

    public static final int DELETE_TASK_INDEX = 7;

    /**
     * Parses an Event from the given input string.
     *
     * @param input the input string containing the event task information
     * @return a formatted string representation of the parsed event task
     * @throws ArgumentNotFoundException if the required arguments are not found in the input
     */
    protected String parseEvent(String input) throws ArgumentNotFoundException {
        if (EVENT_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String event = input.substring(EVENT_TASK_INDEX);
        String[] parts = event.split(" /from ");
        String[] time = parts[1].split(" /to ");
        return parts[0] + " (from: " + time[0] + " to: " + time[1] + ")";
    }

    /**
     * Parses a Deadline from the given input string.
     *
     * @param input the input string containing the deadline task information
     * @return a formatted string representation of the parsed deadline task
     * @throws ArgumentNotFoundException if the required arguments are not found in the input
     */
    protected String parseDeadline(String input) throws ArgumentNotFoundException {
        if (DEADLINE_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        String deadline = input.substring(DEADLINE_TASK_INDEX);
        String[] parts = deadline.split(" /by ");
        return parts[0] + " (by: " + parts[1] + ")";
    }

    /**
     * Parses a Todo from the given input string.
     *
     * @param input the input string containing the todo task information
     * @return a formatted string representation of the parsed todo task
     * @throws ArgumentNotFoundException if the required arguments are not found in the input
     */
    protected String parseTodo(String input) throws ArgumentNotFoundException {
        if (TODO_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        return input.substring(TODO_TASK_INDEX);
    }

    /**
     * Gets the index of the task to mark as done from the input string.
     *
     * @param inputList the list of task objects
     * @param input the input string containing the index of the task to mark as done
     * @return the index of the task to mark as done
     * @throws ArgumentNotFoundException if the required arguments are not found in the input
     * @throws TaskNotFoundException if the specified task index is out of bounds
     */
    protected int getIndexMark(ArrayList<Task> inputList, String input) throws ArgumentNotFoundException, TaskNotFoundException {
        if (MARK_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        int indexMark = Integer.parseInt(input.substring(MARK_TASK_INDEX)) - 1;
        if (indexMark < 0 || indexMark >= inputList.size() || inputList.get(indexMark) == null) {
            throw new TaskNotFoundException();
        }
        return indexMark;
    }

    /**
     * Gets the index of the task to mark as not done from the input string.
     *
     * @param inputList the list of task objects
     * @param input the input string containing the index of the task to unmark as undone
     * @return the index of the task to unmark as undone
     * @throws ArgumentNotFoundException if the required arguments are not found in the input
     * @throws TaskNotFoundException if the specified task index is out of bounds
     */
    protected int getIndexUnmark(ArrayList<Task> inputList, String input) throws ArgumentNotFoundException, TaskNotFoundException {
        if (UNMARK_TASK_INDEX > input.length()) {
            throw new ArgumentNotFoundException();
        }
        int indexUnmark = Integer.parseInt(input.substring(UNMARK_TASK_INDEX)) - 1;
        if (indexUnmark < 0 || indexUnmark >= inputList.size() || inputList.get(indexUnmark) == null) {
            throw new TaskNotFoundException();
        }
        return indexUnmark;
    }

    /**
     * Gets the index of the task to delete from the input string.
     *
     * @param input the input string containing the index of the task to delete
     * @return the index of the task to delete
     */
    protected int getIndexDelete(String input) {
        return Integer.parseInt(input.substring(DELETE_TASK_INDEX)) - 1;
    }
}

