import java.util.ArrayList;

/**
 *  Contains the task list
 *  it has operations to add/delete tasks in the list
 */

public class TaskList {
    public ArrayList<Task> list;

    /**
     * Stores the tasks from the passed in parameter to the attribute list.
     * @param tasksFromFile arraylist that contains all the tasks from the textfile represented as different types of tasks objects
     */
    public TaskList(ArrayList<Task> tasksFromFile){
        list = tasksFromFile;
    }

    /**
     * Checks is an entered event command is valid and if it is it marks the task as done
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user
     * @throws Exception IF there are no task with the specified number
     */
    public void doneEvent(Ui ui, String input){
        try {
            if (list.get(Integer.parseInt(input.substring(5)) - 1) == null) {
                throw new DukeExceptions("unvalid done statement");
            }
            else {
                list.get(Integer.parseInt(input.substring(5)) - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: \n\t" + list.get(Integer.parseInt(input.substring(5)) - 1));
            }
        }
        catch (Exception e) {
            ui.errorMessege("I'm sorry, but there are no task with that number :-(\");\n");
        }
    }

    /**
     * deletes the specifies task from the list
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user
     * @throws Exception IF there are no task with the specified number
     */
    public void deleteEvent(Ui ui, String input){
        try{
            ui.messege("Noted. I've removed this task:\n\t" + list.get(Integer.parseInt(input.substring(7))-1) + "\nNow you have " + list.size() + "tasks in the list" );
            list.remove(Integer.parseInt(input.substring(7)) -1);
        }
        catch(Exception e){
            ui.messege("No task with that number");
        }
    }


    /**
     * Search for a task in the list and displays all the task containing the specifies keyword to the user
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user (search word)
     */
    public void findEvent(Ui ui, String input){
        int i = 1;
        boolean found = false;
        String str1 = "";

        for (Task task : list) {
            if (task.description.contains(input.substring(5))) {
                str1 =  str1 + "\t\n" + i + ". " + task;
                i++;
                found = true;
            }
        }

        if(found){
            ui.messege("\tHere are the matching tasks in your list:" + str1 );
        }
        else{
            ui.messege("\tNo matching tasks in your list");
        }
    }

    /**
     * Creates a new todo event
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user (search word)
     */
    public void addToDo(Ui ui, String input){
        Task task = new ToDo(input.substring(5));
        addTask(ui, task);
    }

    /**
     * Creates a new deadline task
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user (search word)
     * @param date the date that the deadline is by
     * @param time1 the time that the deadline is by
     */
    public void addDeadline(Ui ui, String input, String date, String time1){
        Task task = new Deadline(input.substring(9, input.indexOf("/by")), date + " " + time1);
        addTask(ui, task);
    }

    /**
     * Creates a new event task
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user (search word)
     * @param date the date that the event is by
     * @param time1 the time that the event is by
     * @param time2 the time that the event is by
     */
    public void addEvent(Ui ui, String input, String date, String time1, String time2){
        Task task = new Event(input.substring(6, input.indexOf("/at ")), date + " " + time1 + "-" + time2);
        addTask(ui, task);
    }

    /**
     * Used to add any type of task to the list
     * @param ui Ui object that enables us to interact with the user
     * @param task the task objecctive that will be added to the list
     */
    public void addTask(Ui ui, Task task){
        list.add(task);
        ui.messege("\tGot it. I've added this task:\n\t " + task + "\n\tNow you have " + list.size() + " tasks in the list.");
    }
}
