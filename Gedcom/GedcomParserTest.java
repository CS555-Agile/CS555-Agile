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
            assertEquals(false, GedcomParser.US05(), "User story 05 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    void test_us06(){
//        try {
        assertEquals(true, GedcomParser.US06(), "User story 06 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }

    void test_us03(){
//        try {
        assertEquals(true, GedcomParser.US01(), "User story 01 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    void test_us02(){
//      try {
        assertEquals(true, GedcomParser.US04(), "User story 04 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }

    void test_us01(){
//        try {
        assertEquals(true, GedcomParser.US01(), "User story 01 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    void test_us04(){
//      try {
        assertEquals(true, GedcomParser.US04(), "User story 04 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }

    void test_us07(){
//        try {
        assertEquals(true, GedcomParser.US07(), "User story 07 failed!");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
    void test_us08(){
//      try {
        assertEquals(true, GedcomParser.US08(), "User story 08 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us10(){
//      try {
        assertEquals(true, GedcomParser.US10(), "User story 10 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us17(){
//      try {
        assertEquals(true, GedcomParser.US17(), "User story 17 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us09()
    {
//      try {
        assertEquals(true, GedcomParser.US09(), "User story 09 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us12()
    {
//      try {
        assertEquals(true, GedcomParser.US12(), "User story 12 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us13()
    {
//      try {
        assertEquals(true, GedcomParser.US13(), "User story 13 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us14()
    {
//      try {
        assertEquals(true, GedcomParser.US14(), "User story 14 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us16()
    {
//      try {
        assertEquals(true, GedcomParser.US16(), "User story 16 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us18()
    {
//      try {
        assertEquals(true, GedcomParser.US18(), "User story 18 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us22()
    {
//      try {
        assertEquals(true, GedcomParser.US22(), "User story 22 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us23()
    {
//      try {
        assertEquals(true, GedcomParser.US23(), "User story 23 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us21()
    {
//      try {
        assertEquals(true, GedcomParser.US21(), "User story 21 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us24()
    {
//      try {
        assertEquals(true, GedcomParser.US24(), "User story 24 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us20()
    {
//      try {
        assertEquals(true, GedcomParser.US20(), "User story 20 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us25()
    {
//      try {
        assertEquals(true, GedcomParser.US25(), "User story 25 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us19()
    {
//      try {
        assertEquals(true, GedcomParser.US19(), "User story 19 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us26()
    {
//      try {
        assertEquals(true, GedcomParser.US23(), "User story 26 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us29()
    {
//      try {
        assertEquals(true, GedcomParser.US29(), "User story 29 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us30()
    {
//      try {
        assertEquals(true, GedcomParser.US30(), "User story 30 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us31()
    {
//      try {
        assertEquals(true, GedcomParser.US31(), "User story 31 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us32()
    {
//      try {
        assertEquals(true, GedcomParser.US32(), "User story 32 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us34()
    {
//      try {
        assertEquals(true, GedcomParser.US34(), "User story 34 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us35()
    {
//      try {
        try {
			assertEquals(true, GedcomParser.US35(), "User story 35 failed!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
    void test_us36()
    {
//      try {
        assertEquals(true, GedcomParser.US36(), "User story 36 failed!");
//      } catch (ParseException e) {
//          e.printStackTrace();
//      }
    }
}
 