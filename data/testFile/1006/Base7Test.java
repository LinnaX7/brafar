import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class Base7Test {
    @Test(timeout = 2000)
    public void test1() {
        assertEquals("202", Base7.convertToBase7(100));
    }
    @Test(timeout = 2000)
    public void test2() {
        assertEquals("10", Base7.convertToBase7(7));
    }
    @Test(timeout = 2000)
    public void test3() {
        assertEquals("-24", Base7.convertToBase7(-18));
    }
    @Test(timeout = 2000)
    public void test4() {
        assertEquals("0", Base7.convertToBase7(0));
    }
    @Test(timeout = 2000)
    public void test5() {
        assertEquals("404", Base7.convertToBase7(200));
    }
    @Test(timeout = 2000)
    public void test6() {
        assertEquals("-230", Base7.convertToBase7(-119));
    }
    @Test(timeout = 2000)
    public void test7() {
        assertEquals("3", Base7.convertToBase7(3));
    }
    @Test(timeout = 2000)
    public void test8() {
        assertEquals("-12", Base7.convertToBase7(-9));
    }

}
