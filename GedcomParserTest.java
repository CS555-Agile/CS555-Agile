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
            assertEquals(true, GedcomParser.US05(), "User story 05 failed!");
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
}
 