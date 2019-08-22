import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Level2 {
    public static void main(String[] args){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        String reply = "";
        Scanner input = new Scanner(System.in);
        String[] list = new String[100];
        int i = -1;
        while(!reply.equals("bye")) {
            System.out.println("\t____________________________________________________________\n");
            reply = input.next();
            System.out.println("\t____________________________________________________________");
            if(reply.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________\n");
            }
            else if(reply.equals("list")){
                for(String thing : list){
                    if(thing != null){
                        System.out.println(thing);
                    }
                }
            }
            else{
                i = i + 1;
                list[i] = reply;
                System.out.println("\tadded: " + reply);
            }
        }
    }
}
