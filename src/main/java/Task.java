/**
 * Represents a task that has to be done. The Task class is used as a parent class to the following classes;
 * 1. Deadline
 * 2. Event
 * 3. ToDo
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task - used to set start values to the attributes description and isDone
     * isDone is set to false when task is created
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Displays the status icon of a task so that the user can see if the task have been completed or not
     * @return cross if task is not completed and check is class is completed, in the form of a String.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Used to change status icon from uncompleted to completed
     */
    public void markAsDone(){
        this.isDone = true;
    }

    /**
     * Returns a more user friendly description of a task
     *
     * @return statusicon and description of a class
     */
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toOutput() {
        return "description";
    }
}