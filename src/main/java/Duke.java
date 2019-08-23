import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Duke {
    public static void main(String[] args) {
        System.out.println("\t" + "_".repeat(50) + "\n\tHello I'm Duke\n\tWhat can I do for you?");
        String reply = "start";
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[100];
        int i = 0;    // counter to place stuff at the right index in the list of task's

        while (!reply.equals("bye")) {
            if(i == 99){
                System.out.println("list full, quiting");
                break;
            }
            System.out.println("\t" + "_".repeat(50) + "\n");
            reply = input.nextLine();
            System.out.println("\t" + "_".repeat(50) + "\n");
            String caseX = checkDone(list, reply);

            if (reply.equals("bye")) {
                turnOff();

            } else if (reply.equals("list")) {
                printList(list);

            }
            else if (caseX.equals("case1")) { // "done" but there is nothing on that spot in the list
                System.out.println("No task with that number");
            }

            else if (caseX.equals("case2")) { //"done" and valid number --> mark as done
                list[Integer.parseInt(reply.substring(5))-1].markAsDone();
                System.out.println("Nice! I've marked this task as done: \n\t [" + list[Integer.parseInt(reply.substring(5))-1].getStatusIcon() + "]" + list[Integer.parseInt(reply.substring(5))-1].description);
            }

            else if (caseX.equals("case3")) { //not "done" --> add new task to list
                Task t = new Task(reply);
                list[i] = t;
                System.out.println("\tadded: " + reply);
                i++;
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
                System.out.println(j + ". [" + thing.getStatusIcon() + "] " + thing.description);
                j++;
            }
        }
    }

    public static String checkDone(Task[] list, String reply){
        if(reply.equals("done")){
            return "case1";
        }
        else if(reply.length()>=5){
            if(reply.substring(0,5).toLowerCase().equals("done ") && reply.substring(5).matches("^[1-9][0-9]?$|^100$")){
                if(list[Integer.parseInt(reply.substring(5))-1] != null) {
                    return "case2";
                }
                else{
                    return "case1";
                }
            }
        }
        return "case3";   // if its not case1 or case2 it must be case3
    }
}

