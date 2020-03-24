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
    void test_us16(){
//        try {
            assertEquals(true, GedcomParser.US16(), "User story 12 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    void test_us18(){
//      try {
          assertEquals(true, GedcomParser.US18(), "User story 18 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
  }
    void test_us09(){
//      try {
          assertEquals(true, GedcomParser.US09(), "User story 12 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
  }
}