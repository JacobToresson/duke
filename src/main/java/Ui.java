import java.util.ArrayList;
import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

public class Ui {
    Scanner input; // to be able to take input form user

    public Ui(){
        input = new Scanner(System.in);
    }

    public void welcome(){
        System.out.println("\t" + "_".repeat(50) + "\n\tHello I'm Duke\n\tWhat can I do for you?");
    }

    public String readCommand(){
        return input.nextLine();
    }

    public void printBlankLine(){
        System.out.println("\t" + "_".repeat(50) + "\n");
    }

    public void printList(ArrayList<Task> list){
        int j = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            if (task != null) {
                System.out.println(j + ".  " + task);
                j++;
            } else {
                break;
            }
        }
    }

    public void errorMessege(String messege){
        System.out.println("☹ OOPS!!! " + messege);
    }

    public void messege(String messege){
        System.out.println(messege);
    }
}
