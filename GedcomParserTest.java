
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
}