import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {
    Parser parser = new Parser();
    Ui ui = new Ui();

    @Test
    public void testGetCase(){
        assertEquals(parser.getCase(ui, "bye"),  "bye");
        assertEquals(parser.getCase(ui, "list   "),  "list");
        assertEquals(parser.getCase(ui, "done 65"),  "done ");
        assertEquals(parser.getCase(ui, "todo kjsdhfjsdhfjh skjdhf    "),  "todo ");
        try{
            parser.getCase(ui, "blah");
        }
        catch(Exception e){
            fail();
        }
    }

    @Test
    public void testCheckDate(){
        assertTrue(parser.checkDate("01/02/2019"));
        assertFalse(parser.checkDate("11/40/2019"));
        assertFalse(parser.checkDate("1-12-2019"));
    }

    @Test
    public void testCheckTime(){
        assertTrue(parser.checkTime("18:00"));
        assertFalse(parser.checkTime("25:00"));
        assertFalse(parser.checkTime("kjhserkjhser"));
        assertFalse(parser.checkTime("6"));
    }

}
