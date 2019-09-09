/**
 *  Subclass of Task. Represents a event task with 2 attributes;
 *  1. description: gives a short description of the event, "finish homework" for example
 *  2. at: tells when the event is taking place, for example "01/01/2020 18:00-21:00"
 */

public class Event extends Task {

    protected String at;

    /**
     * Constructor of Event - used to set start values to the attributes description, isDone and at.
     * isDone is set to false when task is created
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /** Used to print Event tasks in a more user friendly way
     * @return user friendly representation of a task in the form of a string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Used to store Event tasks in the desired format in the textfile
     * @return representation of a event class that clearly display that it is a event task, if the task is done or not and the description of the event task
     */
    @Override
    public String toOutput(){
        String j;
        if (isDone) {
            j = "1";
        } else {
            j = "0";
        }
        return "E" + " | " + j + " | " + description + " | " + at +  " | ";
    }
}

