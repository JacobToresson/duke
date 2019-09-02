import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasksFromFile){
        list = tasksFromFile;
    }

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

    public void deleteEvent(Ui ui, String input){
        try{
            ui.messege("Noted. I've removed this task:\n\t" + list.get(Integer.parseInt(input.substring(7))-1) + "\nNow you have " + list.size() + "tasks in the list" );
            list.remove(Integer.parseInt(input.substring(7)) -1);
        }
        catch(Exception e){
            ui.messege("No task with that number");
        }
    }

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

    public void addToDo(Ui ui, String input){
        Task task = new ToDo(input.substring(5));
        addTask(ui, task);
    }

    public void addDeadline(Ui ui, String input, String date, String time1){
        Task task = new Deadline(input.substring(9, input.indexOf("/by")), date + " " + time1);
        addTask(ui, task);
    }

    public void addEvent(Ui ui, String input, String date, String time1, String time2){
        Task task = new Event(input.substring(6, input.indexOf("/at ")), date + " " + time1 + "-" + time2);
        addTask(ui, task);
    }

    public void addTask(Ui ui, Task task){
        list.add(task);
        ui.messege("\tGot it. I've added this task:\n\t " + task + "\n\tNow you have " + list.size() + " tasks in the list.");
    }
}
