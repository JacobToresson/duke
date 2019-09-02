import java.io.*;
import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output
import java.util.*;

public class Duke {
    private Ui ui;;

    public Duke(String filepath) throws IOException {
        ui = new Ui();
    }

    public void run() throws IOException {
        ui.welcome();
        String input;

        Task t;                   // first task object
        int index1 = -1;              // for checking input
        String caseX = "";     // to keep of track of what to doo

        // creating the list of tasks from file
        HandleFile file = new HandleFile();
        file.openFile();
        ArrayList<Task> list = file.readFileAndCreateList();
        file.closeFile();

        while (true) {
            ui.printBlankLine();
            input = ui.readCommand();
            ui.printBlankLine();

            // resetting in each loop
            t = null;
            caseX = "";

            // checking if user is done
            if (input.equals("bye")) {
                    turnOff(file, list);
            }

            // printing list if user inputs "list"
            else if (input.equals("list")) {
                ui.printList(list);
            }

            else{
                input = input + "          "; // to avoid slicing a string that's to short
                try {
                    if(input.substring(0, 5).equals("done ")) {
                        caseX = "done ";
                    }
                    else if(input.substring(0, 5).equals("todo ")) {
                        caseX = "todo ";
                    }
                    else if(input.substring(0, 6).equals("event ")){
                        caseX = "event ";
                    }
                    else if(input.substring(0, 9).equals("deadline ")){
                        caseX = "deadline ";
                    }
                    else if(input.substring(0, 7).equals("delete ")){
                        caseX = "delete ";
                    }
                    else if(input.substring(0,5).equals("find ")){
                        caseX = "find ";
                    }
                    else{
                        throw new DukeExceptions("Unknown commande");
                    }
                input = input.trim();
                }
                catch(DukeExceptions e){
                    ui.errorMessege(" I'm sorry, but I don't know what that means :-(");
                }
            }

            if(caseX.equals("done ")){
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

            else if(caseX.equals("delete ")){
                try{
                    ui.messege("Noted. I've removed this task:\n\t" + list.get(Integer.parseInt(input.substring(7))-1) + "\nNow you have " + list.size() + "tasks in the list" );
                    list.remove(Integer.parseInt(input.substring(7)) -1);

                }
                catch(Exception e){
                    ui.messege("No task with that number");
                }
            }

            else if(caseX.equals("find ")) {
                int i = 1;
                boolean empty = false;
                boolean found = false;
                String str1 = "";
                try {
                    if (input.substring(4).trim().equals("")) {
                        throw new DukeExceptions("Empty find statement ");
                    }
                }
                catch (DukeExceptions e) {
                    ui.errorMessege(" A find statement cannot be empty");
                    empty = true;
                }
                if (!empty) {
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
            }

            else if(caseX.equals("todo ")) {
                try {
                    if (input.substring(4).replaceAll("\\s+", "").equals("")) {
                        throw new DukeExceptions("Unvalid todo statement");
                    }
                    else {
                        t = new ToDo(input.substring(5));
                    }
                }
                catch (DukeExceptions e) {
                    ui.errorMessege("A todo statement cannot be empty");
                }
            }

            else if(caseX.equals("deadline ")){
                try{
                    index1 = input.indexOf("/by");
                    if (index1 == -1) {
                        throw new DukeExceptions("Unvalid deadline input");
                    }
                }
                catch(Exception e){
                    ui.errorMessege("Unvalid deadline input, most contain /by");
                }

                try {
                    if(!checkDate(input.substring(index1 + 4, index1 + 14)) || !checkTime(input.substring(index1 + 15, index1 + 20))) {
                        throw new DukeExceptions("Unvalid deadline input");
                    }
                    else {
                        String date = convertDate(input.substring(index1 + 4));
                        String time1 = convertTime(input.substring(index1 + 15, index1 + 20));
                        t = new Deadline(input.substring(9, index1), date + " " + time1);
                    }
                }
                catch(Exception e) {
                    ui.errorMessege("Unvalid date/time in the deadline input, most be on format: dd/mm/yyyy hh:mm");
                }
            }

            else if(caseX.equals("event ")) {
                try {
                    index1 = input.indexOf("/at ");
                    if (index1 == -1) {
                        throw new DukeExceptions("Unvalid event input");
                    }
                }
                catch (DukeExceptions e) {
                    ui.errorMessege("Unvalid event input, most contain /at");
                }

                try {
                    if (!checkDate(input.substring(index1 + 4, index1 + 14)) || !checkTime(input.substring(index1 + 15, index1 + 20)) || !checkTime(input.substring(index1 + 21, index1 + 26))) {
                        throw new DukeExceptions("Unvalid event input");
                    }
                    else {
                        String date = convertDate(input.substring(index1 + 4));
                        String time1 = convertTime(input.substring(index1 + 15, index1 + 20));
                        String time2 = convertTime(input.substring(index1 + 21, index1 + 26));
                        t = new Event(input.substring(6, index1), date + " " + time1 + "-" + time2);
                    }
                }
                catch (Exception e) {
                    ui.errorMessege("Unvalid date/time in the event input, most be on format: dd/mm/yyyy hh:mm-hh:mm");
                }
            }

            if (t != null) {
                list.add(t);
                ui.messege("\tGot it. I've added this task:\n\t " + t + "\n\tNow you have " + list.size() + " tasks in the list.");
            }
        }
    }

    public void turnOff (HandleFile file, ArrayList<Task> list) throws IOException {
        ui.messege("\tBye. Hope to see you again soon!\n\t");
        ui.printBlankLine();
        HandleFile.updateFile(list);
        file.closeFile();
        System.exit(0);
    }


    public static boolean checkDate (String date){
        return date.matches("^[0,1]?\\d{1}\\/(([0-2]?\\d{1})|([3][0,1]{1}))\\/(([1]{1}[9]{1}[9]{1}\\d{1})|([2-9]{1}\\d{3}))$");
    }

    public static boolean checkTime(String time){
        return time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    }

    public static String convertDate(String date){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

        int index1 = date.indexOf("/");
        int index2 = date.indexOf("/", index1+1);

        String year = date.substring(index2+1, index2+5);
        String day = Integer.valueOf(date.substring(0, index1)).toString();
        String mounth = months[Integer.parseInt(date.substring(index1+1,index2))-1];

        // gives right ending to the number
        String end;
        if(day.equals("1")){
            end = "st";
        }
        else if(day.equals("2")){
            end = "nd";
        }
        else{
            end = "th";
        }
        return day + end + " of " + mounth + " " + year;
    }

    public static String convertTime(String time){
        int hour = Integer.parseInt(time.substring(0,2));
        String detail = "am";
        if(hour > 12 ){
            detail = "pm";
            hour = hour -12;
        }

        String minute = time.substring(3,5);
        if(minute.equals("00")){ return hour + detail; }
        else{ return hour + ":" + minute + detail;}
    }

    public static void blank(){
        System.out.println("\t" + "_".repeat(50) + "\n");
    }

    public static void main(String[] args) throws IOException {
        new Duke("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt").run();
    }
}