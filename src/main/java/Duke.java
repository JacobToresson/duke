import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t" + "_".repeat(50) + "\n\tHello I'm Duke\n\tWhat can I do for you?");
        String reply = "start";
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[100];
        int i = 0;    // counter to place stuff at the right index in the list of task's
        Task t = null;
        int index1;
        boolean command;

        while (!reply.equals("bye")) {
            System.out.println("\t" + "_".repeat(50) + "\n");
            reply = input.nextLine();

            System.out.println("\t" + "_".repeat(50) + "\n");
            command = false;

            if (reply.equals("bye")) {
                turnOff();
                command = true;
            }

            else if (reply.equals("list")) {
                printList(list);
                command = true;
            }

            else if(reply.length()>=5) {
                if (reply.substring(0, 5).toLowerCase().equals("done ")) {
                    try {
                        list[Integer.parseInt(reply.substring(5)) - 1].markAsDone();
                        System.out.println("Nice! I've marked this task as done: \n\t [" + list[Integer.parseInt(reply.substring(5)) - 1].getStatusIcon() + "]" + list[Integer.parseInt(reply.substring(5)) - 1].description);
                        command = true;
                    }
                    catch (Exception e) {
                        System.out.println("No task with that number");
                        command = true;
                    }
                }

                else if (reply.substring(0, 6).toLowerCase().equals("todoo")) {
                    t = new ToDos(reply.substring(6));
                }

                else if ((reply.substring(0, 6).toLowerCase().equals("event"))) {
                    index1 = reply.indexOf("/at ");
                    t = new Event(reply.substring(6, index1), reply.substring(index1 + 4));
                }

                else if (reply.length() > 9) {
                    if (reply.substring(0, 9).toLowerCase().equals("deadline ")) {
                        index1 = reply.indexOf("/by ");
                        t = new Deadline(reply.substring(9, index1), reply.substring(index1 + 4));
                    }
                }
            }

            if(t != null) {
                list[i] = t;
                System.out.println("\t Got it. I've added this task:\n\t " + t);
                i++;
            }
            else if(!command){
                System.out.println("Unknown type of task or command");
            }
        }
    }

    public static void turnOff(){
        System.out.println("\tBye. Hope to see you again soon!\n\t" + "_".repeat(50) + "\n");
    }

    public static void printList(Task[] list) {
        int j = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task thing : list) {
            if (thing != null) {
                System.out.println(String.valueOf(j) + thing);
                j++;
            }
        }
    }
}

