package Gedcom;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class GedcomParserTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        String []args = null;
        GedcomParser.main(args);
    }

    @Test
    void test_us05(){
//        try {
            assertEquals(true, GedcomParser.us05(), "User story 05 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    void test_us06(){
//        try {
        assertEquals(true, GedcomParser.us06(), "User story 06 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
