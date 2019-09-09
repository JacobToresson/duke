import java.util.ArrayList;
import java.util.Scanner; // https://www.programiz.com/java-programming/basic-input-output

/**
 *  Deals with interactions with the user
*/

public class Ui {
    Scanner input; // to be able to take input form user

    public Ui(){
    /**
     * Constructor of Ui class used to assign the input attribute a value in the form in a Scanner object
     */
        input = new Scanner(System.in);
    }

    /**
     * Prints welcome message to user when duke starts
     */
    public void welcome(){
        System.out.println("\t" + "_".repeat(50) + "\n\tHello I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * Reads the next line in the textfile - each line represents a task
     * @return next line in the textfile as a string
     */
    public String readCommand(){
        return input.nextLine();
    }

    /**
     * Prints a blank line
     */
    public void printBlankLine(){
        System.out.println("\t" + "_".repeat(50) + "\n");
    }

    /** Prints all current tasks in the list
     * @param list the list that contains all the different tasks objects
     */
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

    /** Displays a error messege to the user (all error messeges starts with the sad smiley face)
     * @param messege the messege that is displayed to the user
     */
    public void errorMessege(String messege){
        System.out.println("☹ OOPS!!! " + messege);
    }

    /** Displays a  mesege to the user
     * @param messege the messege that is displayed to the user
     */
    public void messege(String messege){
        System.out.println(messege);
    }
}
