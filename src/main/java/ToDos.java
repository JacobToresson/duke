public class ToDos extends Task {
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }

    @Override
    public String toOutput(){
        String j;
        if (isDone) {
            j = "1";
        } else {
            j = "0";
        }
        return "T" + " | " + j + " | " + description + " | ";
    }
}

