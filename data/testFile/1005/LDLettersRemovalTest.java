import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class LDLettersRemovalTest {

    @Test(timeout = 2000)
    public void test1() {
        assertEquals("dabc", LDLettersRemoval.removeLDLetters("abcadabc"));
    }
    @Test(timeout = 2000)
    public void test2() {
        assertEquals("a", LDLettersRemoval.removeLDLetters("a"));
    }
    @Test(timeout = 2000)
    public void test3() {
        assertEquals("b", LDLettersRemoval.removeLDLetters("bbbbbbb"));
    }
    @Test(timeout = 2000)
    public void test4() {
        assertEquals("cf", LDLettersRemoval.removeLDLetters("cffcfccffccfcffcfccfcffccffcfccf"));
    }

    @Test(timeout = 2000)
    public void test5() {
        assertEquals("deforces", LDLettersRemoval.removeLDLetters("codeforces"));
    }

    @Test(timeout = 2000)
    public void test6() {
        assertEquals("ab", LDLettersRemoval.removeLDLetters("bbbaaabbabaab"));
    }

    @Test(timeout = 2000)
    public void test7() {
        assertEquals("hbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", LDLettersRemoval.removeLDLetters("hbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"));
    }

    @Test(timeout = 2000)
    public void test8() {
        assertEquals("sbiojbihxxoutgedy", LDLettersRemoval.removeLDLetters("jgysbiojbihxxoutgedy"));
    }

    @Test(timeout = 2000)
    public void test9() {
        assertEquals("", LDLettersRemoval.removeLDLetters(""));
    }
}
