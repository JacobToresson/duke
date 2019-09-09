import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Deadline task = new Deadline("Wash a car", "01/01/2020 18:00");

    @Test
    public void testToString() {
        assertEquals("[D][✘] Wash a car (by: 01/01/2020 18:00)", task.toString());
    }

    @Test
    public void testMarkAsDone(){
        task.markAsDone();
        System.out.println(task);
        assertEquals("[D][✓] Wash a car (by: 01/01/2020 18:00)", task.toString());
    }

    @Test
    public void testToOutput(){
        assertEquals(task.toOutput(), "D | 0 | Wash a car | 01/01/2020 18:00 | ");
    }
}
