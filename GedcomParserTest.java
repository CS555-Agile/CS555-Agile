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
    void test_us12(){
//        try {
            assertEquals(true, GedcomParser.US12(), "User story 12 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    
}