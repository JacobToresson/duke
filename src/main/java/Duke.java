import java.io.*;
import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output
import java.io.PrintWriter;

public class Duke {
    public static void main(String[] args) throws DukeExceptions, FileNotFoundException {
        System.out.println("\t" + "_".repeat(50) + "\n\tHello I'm Duke\n\tWhat can I do for you?");
        String reply = "start";
        Scanner input = new Scanner(System.in);
        //Task[] list = new Task[100];
        Task t = null;
        int index1;
        boolean command;
        ReadFile file = new ReadFile();
        file.openFile();
        int i = 0;    // counter to place stuff at the right index in the list of task's
        Task[] list = file.readFileAndCreateList();
        for(Task task:list){
            if(task == null) {
                break;
            }
            else{
                i++;
                }
            }
        file.closeFile();
        printList(list);
        String symbol;
        String j;

        while (!reply.equals("bye")) {
            System.out.println("\t" + "_".repeat(50) + "\n");
            reply = input.nextLine();

            System.out.println("\t" + "_".repeat(50) + "\n");
            command = false;
            t = null;

            if (reply.equals("bye")) {
                turnOff();
                command = true;
            } else if (reply.equals("list")) {
                printList(list);
                command = true;
            } else if (reply.length() >= 5) {
                if (reply.substring(0, 5).toLowerCase().equals("done ")) {
                    command = true;
                    try {
                        if (reply.substring(5).matches("^[1-9][0-9]?$|^100$")) {
                            if (list[Integer.parseInt(reply.substring(5)) - 1] == null) {
                                throw new DukeExceptions("No task with that number");
                            } else {
                                list[Integer.parseInt(reply.substring(5)) - 1].markAsDone();
                                System.out.println("Nice! I've marked this task as done: \n\t" + list[Integer.parseInt(reply.substring(5)) - 1]);
                            }
                        } else {
                            throw new DukeExceptions("No task with that number");
                        }
                    } catch (DukeExceptions e) {
                        System.out.println("☹ OOPS!!! No task with that number ");
                    }
                } else if (reply.substring(0, 5).toLowerCase().equals("todoo")) {
                    try {
                        if (reply.substring(5).replaceAll("\\s+", "").equals("")) {
                            throw new DukeExceptions("No task with that number");
                        } else {
                            t = new ToDos(reply.substring(6));
                        }
                    } catch (DukeExceptions e) {
                        command = true;
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                } else if ((reply.substring(0, 5).toLowerCase().equals("event"))) {
                    command = true;
                    index1 = reply.indexOf("/at ");
                    try {
                        if (index1 == -1) {
                            throw new DukeExceptions("Unvalid event input");
                        } else {
                            t = new Event(reply.substring(6, index1), reply.substring(index1 + 4));
                        }
                    } catch (DukeExceptions e) {
                        System.out.println("☹ OOPS!!! An event input most contain the /ay characters.");

                    }
                } else if (reply.length() > 9) {
                    if (reply.substring(0, 9).toLowerCase().equals("deadline ")) {
                        command = true;
                        index1 = reply.indexOf("/by ");
                        try {
                            if (index1 == -1) {
                                throw new DukeExceptions("Unvalid deadline input");
                            } else {
                                t = new Deadline(reply.substring(9, index1), reply.substring(index1 + 4));

                            }
                        } catch (DukeExceptions e) {
                            System.out.println("☹ OOPS!!! An deadline input most contain the /at characters.");
                        }
                    }
                }
            }

            if (t != null && !command ) {
                list[i] = t;
                i++;
                System.out.println("\tGot it. I've added this task:\n\t " + t + "\n\tNow you have " + i + " tasks in the list.");

                try(FileWriter fw = new FileWriter("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    symbol = t.toString().substring(1,2);
                    System.out.println(symbol);

                    if(t.isDone){
                        j = "1";
                    }
                    else {
                        j = "0";
                    }
                    String output = symbol + " | " + j + " | " + t.description + " | ";
                    if(symbol.equals("E") || symbol.equals("D")) {
                        symbol = symbol + t.by + " | ";
                    }
                    out.println(output);
                }


                catch (IOException e) {
                    System.out.println("something whent wrong when printing to the file");
                }


            } else if (!command) {
                try {
                    throw new DukeExceptions("Unvalid command");
                } catch (DukeExceptions e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }

    public static void turnOff() {
        System.out.println("\tBye. Hope to see you again soon!\n\t" + "_".repeat(50) + "\n");
    }

    public static void printList(Task[] list) {
        int j = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task thing : list) {
            if (thing != null) {
                System.out.println(String.valueOf(j) + ".  " + thing);
                j++;
            }
        }
    }
}
