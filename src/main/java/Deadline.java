public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toOutput(){
        String j;
        if (isDone) {
            j = "1";
        } else {
            j = "0";
        }
        return "D" + " | " + j + " | " + description + " | " + by +  " | ";
    }
}