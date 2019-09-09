/**
 *  Subclass of Task. Represents a deadline task with 2 attributes;
 *  1. description: gives a short description of the deadline, "finish homework" for example
 *  2. by: tells when the deadline is set, for example "01/01/2020 18:00"
 */

public class Deadline extends Task {
    protected String by;
    /**
     * Constructor of Deadline - used to set start values to the attributes description, isDone and by.
     * isDone is set to false when task is created
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /** Used to print Deadline tasks in a more user friendly way
     * @return user friendly representation of a task in the form of a string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Used to store Deadline tasks in the desired format in the textfile
     * @return representation of a deadline class that clearly display that it is a deadline task, if the task is done or not and the description of the deadline task
     */
    @Override
    public String toOutput(){
        String j;
        if (isDone) {
            j = "1";
        } else {
            j = "0";
        }
        return "D" + " | " + j + " | " + description + " | " + by +  " | ";
    }
}