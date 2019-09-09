/**
 *   Deals with making sense of the user command - for example it checks if the entered command is valid and that the time and dates entered is valid
 */

public class Parser {

    /**
     * Returns the command that the user have entered so that Duke knows what to do next
     * if the command entered by the user is unvalid an duke exception is thrown
     *
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user
     * @throws DukeExceptions IF input does not match a valid command
     * @return the command that the user have inputed in a format that duke understands
     */
    public String getCase(Ui ui, String input){
        String[] commands = {"bye", "list", "done ", "todo ", "event ", "deadline ", "delete ", "find "};
        String caseX = "";
        input = input + "          "; // to avoid slicing a string that's to short

        try {
            for (String command : commands) {
                if (input.substring(0, command.length()).equals(command)) {
                    return command;
                }
            }
            throw new DukeExceptions("Unvalid command");
        }
        catch (DukeExceptions e) {
            ui.errorMessege(" I'm sorry, but I don't know what that means :-(");
        }
        return caseX;
    }

    /**
     * Returns true if the date that the user inputted is valid
     *
     * @param date the date inputed by the user
     * @return boolean that tells if date is valid or not
     */
    public boolean checkDate (String date){
        return date.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$");
    }

    /**
     * Returns true if the time that the user inputted is valid
     *
     * @param time the time inputed by the user
     * @return boolean that tells if time is valid or not
     */
    public boolean checkTime(String time){
        return time.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    }

    /**
     * Returns a more user friendly version of the date that the user inputted
     *
     * @param date the date inputted by the user - on format dd/mm/yyyy
     * @return converted date in a more user friendly way
     */
    public String convertDate(String date){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

        int index1 = date.indexOf("/");
        int index2 = date.indexOf("/", index1+1);

        String year = date.substring(index2+1, index2+5);
        String day = Integer.valueOf(date.substring(0, index1)).toString();

        String month = months[Integer.parseInt(date.substring(index1 + 1, index2)) - 1];

            // gives right ending to the number
        String end;
        if(day.equals("1")){
            end = "st";
        }
        else if(day.equals("2")){
            end = "nd";
        }
        else{
            end = "th";
        }
        return day + end + " of " + month + " " + year;
    }

    /**
     * Returns a more user friendly version of the time that the user inputted
     *
     * @param time the date inputed by the user - on format hh:mm
     * @return converted time in a more user friendly way, using am and pm
     */
    public String convertTime(String time){
        int hour = Integer.parseInt(time.substring(0,2));
        String detail = "am";
        if(hour > 12 ){
            detail = "pm";
            hour = hour -12;
        }

        String minute = time.substring(3,5);
        if(minute.equals("00")){ return hour + detail; }
        else{ return hour + ":" + minute + detail;}
    }
    /**
     * Returns a boolean that tells if a find command is empty or not
     *
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user
     * @throws DukeExceptions, IF an empty Find command was entered
     * @return boolean that tells if find command is valid or not
     */
    public boolean checkFind(Ui ui, String input ){
        try {
            if (input.substring(4).trim().equals("")) {
                throw new DukeExceptions("Empty find statement ");
            } else {
                return true;
            }
        } catch (DukeExceptions e) {
            ui.errorMessege(" A find statement cannot be empty");
        }
        return false;
    }

    /**
     * Returns a boolean that tells if a todo command is empty or not
     *
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user
     * @throws DukeExceptions, IF an empty todo command was entered
     * @return boolean that tells if todo command is valid or not
     */
    public boolean checkToDo(Ui ui, String input){
        try {
            if (input.substring(4).replaceAll("\\s+", "").equals("")) {
                throw new DukeExceptions("Unvalid todo statement");
            } else {
                return true;
            }
        } catch (DukeExceptions e) {
            ui.errorMessege("A todo statement cannot be empty");
        }
        return false;
    }

    /**
     * Returns a boolean that tells if a Deadline command is empty or not and if it is valid (date and time correctly inputted)
     *
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user
     * @throws Exception, IF an empty or unvalid Deadline command was entered
     * @return boolean that tells if deadline command is valid or not
     */
    public boolean checkDeadline(Ui ui, String input){
        int index1;
        try {
            index1 = input.indexOf("/by");
            if (index1 == -1) {
                throw new DukeExceptions("Unvalid deadline input");
            }
        } catch (Exception e) {
            ui.errorMessege("Unvalid deadline input, most contain /by");
            return false;
        }
        try {
            if (!checkDate(input.substring(index1 + 4, index1 + 14)) || !checkTime(input.substring(index1 + 15, index1 + 20))) {
                throw new DukeExceptions("Unvalid deadline input");
            } else {
                return true;
            }
        } catch (Exception e) {
            ui.errorMessege("Unvalid date/time in the deadline input, most be on format: dd/mm/yyyy hh:mm");
            return false;
        }
    }

    /**
     * Returns a boolean that tells if a Event command is empty or not and if it is valid (date and time correctly inputted)
     *
     * @param ui Ui object that enables us to interact with the user
     * @param input the string that was entered by the user
     * @throws DukeExceptions, IF an empty or unvalid Event command was entered
     * @return boolean that tells if Event command is valid or not
     */
    public boolean checkEvent(Ui ui, String input){
        int index1;
        try {
            index1 = input.indexOf("/at");
            if (index1 == -1) {
                throw new DukeExceptions("Unvalid event input");
            }
        } catch (Exception e) {
            ui.errorMessege("Unvalid event input, most contain /at");
            return false;
        }
        try {
            if (!checkDate(input.substring(index1 + 4, index1 + 14)) || !checkTime(input.substring(index1 + 15, index1 + 20)) || !checkTime(input.substring(index1 + 21, index1 + 26))) {
                throw new DukeExceptions("Unvalid event input");
            } else {
                return true;
            }
        } catch (Exception e) {
            ui.errorMessege("Unvalid date/time in the event input, most be on format: dd/mm/yyyy hh:mm-hh:mm");
            return false;
        }
    }
}
