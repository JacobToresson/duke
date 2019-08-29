public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + by + ")";
    }

    @Override
    public String toOutput(){
        String j;
        if (isDone) {
            j = "1";
        } else {
            j = "0";
        }
        return "E" + " | " + j + " | " + description + " | " + by +  " | ";
    }
}

