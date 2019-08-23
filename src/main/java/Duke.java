import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        String reply = "";
        Scanner input = new Scanner(System.in);
        while(!reply.equals("bye")) {
            System.out.println("\t____________________________________________________________\n");
            reply = input.next();
            System.out.println("\t____________________________________________________________");
            if(!reply.equals("bye")){
                System.out.println("\t" + reply);
            }
        }
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________\n");

    }
}



