import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Level2 {
    public static void main(String[] args){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        String reply = "";
        Scanner input = new Scanner(System.in);
        while(!reply.equals("bye")) {
            System.out.println("\t____________________________________________________________\n");
            reply = input.next();
            System.out.println("\t____________________________________________________________");
            if(reply.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________\n");
            }
            else if(reply.equals("list")){
                System.out.println("LIIISTANNNNN");
            }
            else{
                System.out.println("\tadded: " + reply);
            }
        }
    }
}
