/**
 * Class that inherits from Javas Exception class that is used to handle customized duke exceptions
 *  1. description: gives a short description of the event, "finish homework" for example
 *  2. at: tells when the event is taking place, for example "01/01/2020 18:00-21:00"
 */

//https://www.javatpoint.com/custom-exception https://www.codejava.net/java-core/exception/how-to-create-custom-exceptions-in-java

public class DukeExceptions extends Exception {
    DukeExceptions(String message){
        super(message);
    }
}
