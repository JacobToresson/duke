import java.io.*;
import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Duke {
    public static void main(String[] args) throws IOException {
        System.out.println("\t" + "_".repeat(50) + "\n\tHello I'm Duke\n\tWhat can I do for you?");
        String reply;
        Scanner input = new Scanner(System.in); // to be able to take input form user

        Task t;           // first task object
        int index1;       // for checking input
        boolean command;  // to keep track of what has happend

        // creating the list of tasks from file
        HandleFile file = new HandleFile();
        file.openFile();
        Task[] list = file.readFileAndCreateList();
        file.closeFile();

        // counting the amount of tasks in list
        int i = countList(list);

        while (true) {
            System.out.println("\t" + "_".repeat(50) + "\n");
            reply = input.nextLine();
            System.out.println("\t" + "_".repeat(50) + "\n");

            // resetting in each loop
            command = false;
            t = null;

            // checking if user is done
            if (reply.equals("bye")) {
                turnOff(file, list);
                System.out.println("\t" + "_".repeat(50) + "\n");
                break;

            // printing list if user inputs "list"
            } else if (reply.equals("list")) {
                printList(list);
                command = true;
            }

            //(to avoid slicing a string outside of the string)
            else if (reply.length() >= 5) {

                // checking if user input in "done"
                if (reply.substring(0, 5).toLowerCase().equals("done ")) {
                    command = true;

                    // checking if "done" was followed by a valid number
                    try {
                        if (reply.substring(5).matches("^[1-9][0-9]?$|^100$")) {
                            if (list[Integer.parseInt(reply.substring(5)) - 1] == null) {
                                throw new DukeExceptions("No task with that number");
                            } else {
                                list[Integer.parseInt(reply.substring(5)) - 1].markAsDone();
                                System.out.println("Nice! I've marked this task as done: \n\t" + list[Integer.parseInt(reply.substring(5)) - 1]);
                            }
                        }
                        else {
                            throw new DukeExceptions("No task with that number");
                        }
                    }
                    catch (DukeExceptions e) {
                        System.out.println("☹ OOPS!!! No task with that number ");
                    }
                }

                // checking if user input "todoo"
                else if (reply.substring(0, 5).toLowerCase().equals("todoo")) {
                    try {
                        if (reply.substring(5).replaceAll("\\s+", "").equals("")) {
                            throw new DukeExceptions("No task with that number");
                        } else {
                            t = new ToDos(reply.substring(6));
                        }
                    }
                    catch (DukeExceptions e) {
                        command = true;
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                }

                // checking if user input "event"
                else if ((reply.substring(0, 5).toLowerCase().equals("event"))) {
                    index1 = reply.indexOf("/at ");
                    try {
                        if (index1 == -1) {
                            throw new DukeExceptions("Unvalid event input");
                        } else {
                            t = new Event(reply.substring(6, index1), reply.substring(index1 + 4));
                        }
                    } catch (DukeExceptions e) {
                        command = true;
                        System.out.println("☹ OOPS!!! An event input most contain the /at characters.");
                    }
                }

                //(to avoid slicing a string outside of the string)
                else if (reply.length() > 9) {

                    // checking if user input "deadline "
                    if (reply.substring(0, 9).toLowerCase().equals("deadline ")) {
                        index1 = reply.indexOf("/by ");
                        try {
                            if (index1 == -1) {
                                throw new DukeExceptions("Unvalid deadline input");
                            } else {
                                t = new Deadline(reply.substring(9, index1), reply.substring(index1 + 4));
                            }
                        }
                        catch (DukeExceptions e) {
                            command = true;
                            System.out.println("☹ OOPS!!! An deadline input most contain the /at characters.");
                        }
                    }
                }
            }

            if (t != null && !command ) {
                list[i] = t;
                i++;
                System.out.println("\tGot it. I've added this task:\n\t " + t + "\n\tNow you have " + i + " tasks in the list.");
            }

            else if (!command) {
                try {
                    throw new DukeExceptions("Unvalid command");
                }
                catch (DukeExceptions e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }

    public static void turnOff(HandleFile file, Task[] list) throws IOException {
        System.out.println("\tBye. Hope to see you again soon!\n\t" );
        HandleFile.updateFile(list);
        file.closeFile();
    }

    public static int countList(Task[] list){
        int numberOfTask = 0;
        for(Task task:list){
            if(task == null) {
                break;
            }
            else{
                numberOfTask++;
            }
        }
        return numberOfTask;
    }

    public static void printList(Task[] list) {
        int j = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            if (task != null) {
                System.out.println(String.valueOf(j) + ".  " + task);
                j++;
            }
            else{
                break;
            }
        }
    }
}
