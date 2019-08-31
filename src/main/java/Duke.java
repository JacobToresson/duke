import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Duke {
    public static void main(String[] args) throws IOException {
        System.out.println("\t" + "_".repeat(50) + "\n\tHello I'm Duke\n\tWhat can I do for you?");
        String reply;
        Scanner input = new Scanner(System.in); // to be able to take input form user

        Task t;                   // first task object
        int index1 = -1;              // for checking input
        boolean command;        // to keep track of what has happend
        String caseX = "";     // to keep of track of what to doo

        // creating the list of tasks from file
        HandleFile file = new HandleFile();
        file.openFile();
        Task[] list = file.readFileAndCreateList();
        file.closeFile();

        // counting the amount of tasks in list
        int i = countList(list);

        while (true) {
            //getting user input
            blank();
            reply = input.nextLine();
            blank();

            // resetting in each loop
            t = null;
            caseX = "";

            // checking if user is done
            if (reply.equals("bye")) {
                turnOff(file, list);
            }

            // printing list if user inputs "list"
            else if (reply.equals("list")) {
                printList(list);
            }

            else{
                reply = reply + "          "; // to avoid slicing a string that's to short
                try {
                    if(reply.substring(0, 5).equals("done ")) {
                        caseX = "done ";
                    }
                    else if(reply.substring(0, 5).equals("todo ")) {
                        caseX = "todo ";
                    }
                    else if(reply.substring(0, 6).equals("event ")){
                        caseX = "event ";
                    }
                    else if(reply.substring(0, 9).equals("deadline ")){
                        caseX = "deadline ";
                    }
                    else{
                        throw new DukeExceptions("Unknown commande");
                    }
                reply = reply.trim();
                }
                catch(DukeExceptions e){
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

            if(caseX.equals("done ")){
                try {
                    if (list[Integer.parseInt(reply.substring(5)) - 1] == null) {
                        throw new DukeExceptions("unvalid done statement");
                    }
                    else {
                        list[Integer.parseInt(reply.substring(5)) - 1].markAsDone();
                        System.out.println("Nice! I've marked this task as done: \n\t" + list[Integer.parseInt(reply.substring(5)) - 1]);
                    }
                }
                catch (Exception e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but there are no task with that number :-(\");\n");
                }
            }

            else if(caseX.equals("todo ")) {
                try {
                    if (reply.substring(4).replaceAll("\\s+", "").equals("")) {
                        throw new DukeExceptions("Unvalid todo statement");
                    }
                    else {
                        t = new ToDo(reply.substring(5));
                    }
                }
                catch (DukeExceptions e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }

            else if(caseX.equals("deadline ")){
                try{
                    index1 = reply.indexOf("/by");
                    if (index1 == -1) {
                        throw new DukeExceptions("Unvalid deadline input");
                    }
                }
                catch(Exception e){
                    System.out.println("☹ OOPS!!! Unvalid deadline input, most contain /by");
                }

                try {
                    if(!checkDate(reply.substring(index1 + 4, index1 + 14)) || !checkTime(reply.substring(index1 + 15, index1 + 20))) {
                        throw new DukeExceptions("Unvalid deadline input");
                    }
                    else {
                        String date = convertDate(reply.substring(index1 + 4));
                        String time1 = convertTime(reply.substring(index1 + 15, index1 + 20));
                        t = new Deadline(reply.substring(9, index1), date + " " + time1);
                    }
                }
                catch(Exception e) {
                    System.out.println("☹ OOPS!!! Unvalid date/time in the deadline input, most be on format: dd/mm/yyyy hh:mm");
                }
            }

            else if(caseX.equals("event ")) {
                try {
                    index1 = reply.indexOf("/at ");
                    if (index1 == -1) {
                        throw new DukeExceptions("Unvalid event input");
                    }
                }
                catch (DukeExceptions e) {
                    System.out.println("☹ OOPS!!! Unvalid event input, most contain /at");
                }

                try {
                    if (!checkDate(reply.substring(index1 + 4, index1 + 14)) || !checkTime(reply.substring(index1 + 15, index1 + 20)) || !checkTime(reply.substring(index1 + 21, index1 + 26))) {
                        throw new DukeExceptions("Unvalid event input");
                    }
                    else {
                        String date = convertDate(reply.substring(index1 + 4));
                        String time1 = convertTime(reply.substring(index1 + 15, index1 + 20));
                        String time2 = convertTime(reply.substring(index1 + 21, index1 + 26));
                        t = new Event(reply.substring(6, index1), date + " " + time1 + "-" + time2);
                    }
                }
                catch (Exception e) {
                    System.out.println("☹ OOPS!!! Unvalid date/time in the event input, most be on format: dd/mm/yyyy hh:mm-hh:mm");
                }
            }

            if (t != null) {
                list[i] = t;
                i++;
                System.out.println("\tGot it. I've added this task:\n\t " + t + "\n\tNow you have " + i + " tasks in the list.");
            }
        }
    }

    public static void turnOff (HandleFile file, Task[]list) throws IOException {
        System.out.println("\tBye. Hope to see you again soon!\n\t");
        System.out.println("\t" + "_".repeat(50) + "\n");
        HandleFile.updateFile(list);
        file.closeFile();
        System.exit(0);
    }

    public static int countList (Task[]list){
        int numberOfTask = 0;
        for (Task task : list) {
            if (task == null) {
                break;
            } else {
                numberOfTask++;
            }
        }
        return numberOfTask;
    }

    public static void printList (Task[]list){
        int j = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            if (task != null) {
                System.out.println(String.valueOf(j) + ".  " + task);
                j++;
            } else {
                break;
            }
        }
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
}