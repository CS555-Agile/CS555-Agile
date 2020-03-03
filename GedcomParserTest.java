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
    void test_us03(){
//        try {
            assertEquals(true, GedcomParser.US03(), "User story 03 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    void test_us02(){
//      try {
          assertEquals(true, GedcomParser.US02(), "User story 02 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
  }
}