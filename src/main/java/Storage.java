import java.io.*; // https://www.youtube.com/watch?v=3RNYUKxAgmw
import java.util.*;
import java.lang.*;

public class Storage {
    private Scanner x;
    private String filepath;

    public Storage(String filepath) {
        try {
            File taskFile = new File("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt");
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
                x = new Scanner(taskFile);
        }
        catch(Exception e){
            System.out.println("File not found");
        }
    }

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

    public void closeFile(){
        x.close();
    }

    public void updateFile(ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt", false);
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



