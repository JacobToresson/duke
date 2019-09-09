/**
 *  Subclass of Task. Represents a todo task with 1 attribute;
 *  1. description: gives a short description of what has to be done, "wash car" for example
 */

public class ToDo extends Task {
    /**
     * Constructor of ToDo - used to set start values to the attributes description and isDone
     * isDone is set to false when task is created
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Used to print todo tasks in a more user friendly way
     * @return user friendly representation of a task in the form of a string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    /**
     * Used to store todo tasks in the desired format in the textfile
     * @return representation of a todo class that clearly display that it is a todo task, if the task is done of not and the description of the todo task
     */
    @Override
    public String toOutput(){
        String j;
        if (isDone) {
            j = "1";
        } else {
            j = "0";
        }
        return "T" + " | " + j + " | " + description + " | ";
    }
}

