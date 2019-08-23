import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Level2 {
    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        String reply = "";
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[100];

        int i = 0;    // counter to place stuff at the right index in the list of task's
        int j = 0;    // counter thats used when printing list

        while (!reply.equals("bye")) {
            System.out.println("\t____________________________________________________________\n");
            reply = input.nextLine();
            System.out.println("\t____________________________________________________________");

            if (reply.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________\n"); }

            else if (reply.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                j = 0;
                for (Task thing : list) {
                    if (thing != null) {
                        j = j + 1;
                        System.out.println(j + ". [" + thing.getStatusIcon() + "] " + thing.description); } } }

            else {
                if (reply.length() > 5) {
                    if (reply.substring(0, 5).equals("done ") && reply.substring(5).matches("-?\\d+(\\.\\d+)?")) { //regex for all number
                        int tasknum = Integer.parseInt(reply.substring(5))-1;
                        if (tasknum < i && tasknum > -1) {
                            list[tasknum].markAsDone();
                            System.out.println("Nice! I've marked this task as done: \n\t [" + list[tasknum].getStatusIcon() + "]" + list[tasknum].description);
                            reply = "";
                        } else {
                            System.out.println("No task with that number");
                            reply = ""; } } }

                if (reply != "") {
                    Task t = new Task(reply);
                    list[i] = t;
                    System.out.println("\tadded: " + reply);
                    i = i + 1;
                } } } }
}



