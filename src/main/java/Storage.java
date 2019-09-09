import java.io.*; // https://www.youtube.com/watch?v=3RNYUKxAgmw
import java.nio.file.FileSystemNotFoundException;
import java.util.*;
import java.lang.*;

/**
 *  Deals with loading tasks from the file and saving tasks in the file .
 *  A Storage object has the following attributes;
 *  1. x: A scanner that enables ut to take input from the user
 *  2. filepath: gives the path to the textfile that the tasks are stored in
 */

public class Storage {
    private Scanner x;
    private String filepath;


    /**
     * Constructor of storage creates a file where the tasks will be stored if it does not exist.
     *
     * @param filepathh specifies where the textfile with the tasks should be if it exist
     */
    public Storage(String filepathh) {
        filepath = filepathh;
        try {
            File taskFile = new File(filepath);
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
                x = new Scanner(taskFile);
        }
        catch(Exception e){
            System.out.println("File not found");
        }
    }


    /**
     *  Creates and returns the list that contains the different tasks based on what is written in the textfile
     *  identifier: used to identify diffrent types of tasks
     *  completed: identifies if the task in completed or not
     *  description: used to store a tasks description
     *  test: to check if we are at the end of a line or not
     *  when: used to store a tasks time and data
     *  task: the actual task that will be added to the list
     *
     * @return Arraylist containing the different task objects
     */
    public ArrayList<Task> readFileAndCreateList(){
        ArrayList<Task> list = new ArrayList<>();
        String identifier;
        String completed;
        String description;
        String test;
        String when;
        Task task;

        while(x.hasNext()) {
            identifier = x.next();
            x.next();
            completed = x.next();
            x.next();
            description = x.next();

            while (x.hasNext()) {
                test = x.next();
                if (test.equals("|")) {
                    break;
                }
                else {
                    description = description + " " + test;
                }
            }
            if(identifier.equals("T")) {
                task = new ToDo(description);
            }

            else {
                when = x.next();
                while (x.hasNext()) {
                    test = x.next();
                    if (test.equals("|")) {
                        break;
                    }
                    else {
                        when = when + " " + test;
                    }
                }
                if (identifier.equals("D")) {
                    task = new Deadline(description, when);
                }
                else if (identifier.equals("E")) {
                    task = new Event(description, when);
                }
                else{
                    System.out.println("Unidentified kind of task");
                    break;
                }
            }
            if (completed.equals("1")) {
                task.markAsDone();
            }
            list.add(task);
        }
        return list;
    }

    /**
     * Closes the file so that the result will be saved - runs when exiting duke
     */
    public void closeFile(){
        x.close();
    }

    /**
     * Runs when duke is closing and updates the textfile after what have been
     *
     * @param list the list of tasks that will be stored in the textfile
     */
    public void updateFile(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(filepath, false);
            for (Task task : list) {
                if (task == null) {
                    break;
                }
                String output = task.toOutput();
                fw.append(output);
                fw.append("\n");
            }
            fw.flush();
            fw.close();
        }
        catch(Exception e){
            System.out.println("Something went wrong while printing to the file");
        }
    }
}



