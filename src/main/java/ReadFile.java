import java.io.*; // https://www.youtube.com/watch?v=3RNYUKxAgmw
import java.util.*;
import java.lang.*;
import java.io.PrintWriter;

public class ReadFile {
    private Scanner x;
    public void openFile(){
        try{
            x = new Scanner(new File("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt"));
        }
        catch(Exception e) {
            System.out.println("Could not find file");
        }
    }
    public Task[] readFileAndCreateList(){
        Task[] list = new Task[100];
        int i = 0;
        String identifier;
        String next;
        String completed;
        String description;
        String test;
        String when;

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
                list[i] = new ToDos(description);
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
                    list[i] = new Deadline(description, when);
                }
                else if (identifier.equals("E")) {
                    list[i] = new Event(description, when);
                }
                else{
                    System.out.println("NÅGOT ÄR FEL");
                }
            }
            if (completed.equals("1")) {
                list[i].markAsDone();
            }
            i++;
        }
        return list;
    }

    public void closeFile(){
        x.close();
    }

    public static void updateFile(Task[] list) throws IOException {
        String symbol;
        String j;
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter out;

        try {
            fw = new FileWriter("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt", false);
            bw = new BufferedWriter(fw);
            for (Task task : list) {
                if (task == null) {
                    break;
                }
                symbol = task.toString().substring(1, 2);
                if (task.isDone) {
                    j = "1";
                } else {
                    j = "0";
                }
                String output = symbol + " | " + j + " | " + task.description + " | ";
                if (symbol.equals("E") || symbol.equals("D")) {
                    symbol = symbol + task.by + " | ";
                }
                fw.append(output);
            }
            fw.flush();
            fw.close();
        }
        catch(Exception e){
            System.out.println("Something went wrong while printing the file");
        }
    }
}



