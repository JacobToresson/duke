/**
 *  Class represents an Duke object. Duke enables its users to create and manipulate lists of diffrent kinds of tasks.
 *  A duke object has the following attributes;
 *  1. ui: deals with interactions with the user
 *  2. storage: deals with loading tasks from the file and saving tasks in the file
 *  3. parser : deals with making sense of the user command
 *  4. taskList: contains the task list e.g., it has operations to add/delete tasks in the list
 *
 * @author  Jacob Toresson
 */


public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    private Duke(String filepath) {

    /**
     * Constructor of Duke class that is used to specify start values to dukes attributes
     * @param filepath used to specify the filepath to the textfile that the tasks will be loaded from and stored to
     */

        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList(storage.readFileAndCreateList());
        parser = new Parser();
    }

    private void run() {
        /**
         * Method used to run duke until the user inputs "bye"
         */

        ui.welcome();
        String input;
        String caseX = "";     // to keep of track of what to doo

        while (true) {
            ui.printBlankLine();
            input = ui.readCommand();
            ui.printBlankLine();

            caseX = parser.getCase(ui, input);

            switch (caseX) {
                case "bye":
                    turnOff();
                    break;
                case "list":
                    ui.printList(taskList.list);
                    break;
                case "done ":
                    taskList.doneEvent(ui, input);
                    break;
                case "delete ":
                    taskList.deleteEvent(ui, input);
                    break;
                case "find ":
                    if (parser.checkFind(ui, input)) {
                        taskList.findEvent(ui, input);
                    }
                    break;
                case "todo ":
                    if (parser.checkToDo(ui, input)) {
                        taskList.addToDo(ui, input);
                    }
                    break;
                case "deadline ":
                    if (parser.checkDeadline(ui, input)) {
                        String date = parser.convertDate(input.substring(input.indexOf("/by") + 4));
                        String time1 = parser.convertTime(input.substring(input.indexOf("/by") + 15, input.indexOf("/by") + 20));
                        taskList.addDeadline(ui, input, date, time1);
                    }
                    break;
                case "event ":
                    if (parser.checkEvent(ui, input)) {
                        String date = parser.convertDate(input.substring(input.indexOf("/at") + 4));
                        String time1 = parser.convertTime(input.substring(input.indexOf("/at") + 15, input.indexOf("/at") + 20));
                        String time2 = parser.convertTime(input.substring(input.indexOf("/at") + 21, input.indexOf("/at") + 26));
                        taskList.addEvent(ui, input, date, time1, time2);
                    }
                    break;
            }
        }
    }

    private void turnOff() {
        /**
         * Used to turn off duke when the user have entered "bye"
         */

        ui.messege("\tBye. Hope to see you again soon!\n\t");
        ui.printBlankLine();
        storage.updateFile(taskList.list);
        storage.closeFile();
        System.exit(0);
    }

    public static void main(String[] args) {
        /**
         * main method of duke
         */

        new Duke("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt").run();
    }
}

