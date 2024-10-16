import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class ScientificNotationTest {

    public static float DELTA = 1E-6f;

    @Test(timeout = 2000)
    public void test1() {
        assertEquals(854.9, ScientificNotation.getValueFromAeB("8.549e2"), DELTA);
    }
    @Test(timeout = 2000)
    public void test2() {
        assertEquals(804.9, ScientificNotation.getValueFromAeB("8.049e2"), DELTA);
    }
    @Test(timeout = 2000)
    public void test3() {
        assertEquals(1.33, ScientificNotation.getValueFromAeB("1.33e0"), DELTA);
    }

    @Test(timeout = 2000)
    public void test4() {
        assertEquals(-0.133, ScientificNotation.getValueFromAeB("-1.33e-1"), DELTA);
    }

    @Test(timeout = 2000)
    public void test5() {
        assertEquals(-0.103, ScientificNotation.getValueFromAeB("-1.03e-1"), DELTA);
    }

    @Test(timeout = 2000)
    public void test6() {
        assertEquals(-0.1, ScientificNotation.getValueFromAeB("-1e-1"), DELTA);
    }

    @Test(timeout = 2000)
    public void test7() {
        assertEquals(-0.0005, ScientificNotation.getValueFromAeB("-5e-4"), DELTA);
    }

    @Test(timeout = 2000)
    public void test8() {
        assertEquals(120, ScientificNotation.getValueFromAeB("+1.2e+2"), DELTA);
    }

    @Test(timeout = 2000)
    public void test9() {
        assertEquals(0.012, ScientificNotation.getValueFromAeB("+1.2e-2"), DELTA);
    }

    @Test(timeout = 2000)
    public void test10() {
        assertEquals(-0.0102, ScientificNotation.getValueFromAeB("-1.02e-2"), DELTA);
    }
}
