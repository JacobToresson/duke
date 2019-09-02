public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    private Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        taskList = new TaskList(storage.readFileAndCreateList());
        parser = new Parser();
    }

    private void run() {
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
        ui.messege("\tBye. Hope to see you again soon!\n\t");
        ui.printBlankLine();
        storage.updateFile(taskList.list);
        storage.closeFile();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("/Users/JacobT/Desktop/PLUGG/CS1231/duke/src/main/java/duke.txt").run();
    }
}