import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Level2 {
    public static void main(String[] args) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        String reply = "";
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[100];
        int i = -1;
        while (!reply.equals("bye")) {
            int j = 0;
            System.out.println("\t____________________________________________________________\n");
            reply = input.next();
            System.out.println("\t____________________________________________________________");
            if (reply.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________\n");
            } else if (reply.equals("list")) {
                j = j + 1;
                System.out.println("Here are the tasks in your list:");
                for (Task thing : list) {
                    if (thing != null) {
                        System.out.println(j + ". " + thing.getStatusIcon() + " " + thing.description);
                    }
                }
            } else {
                System.out.println(reply.substring(0, 5));
                try {
                    String test = reply;
                    if (test.substring(0, 5).equals("done ") && Integer.parseInt(test.substring(6)) <= 100) {
                        System.out.println("asdksdlwkjd");
                    }
                }
                catch(Exception e){
                    System.out.println("akkkkkkkkkkksdksdlwkjd");
                    i = i + 1;
                    Task t = new Task(reply);
                    list[i] = t;
                    System.out.println("\tadded: " + reply);
                    }
                }
            }
        }
    }




