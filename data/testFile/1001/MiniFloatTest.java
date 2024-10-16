import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MiniFloatTest {
	public static float DELTA = 1E-6f;

	@Test(timeout = 2000)
	public void test1() {
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("00100110") - 28) < DELTA);
		System.out.println("test 1 passed");
		
	}

	@Test(timeout = 2000)
	public void test2() {
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("10100110") - -28) < DELTA);
		System.out.println("test 2 passed");
		
	}

	@Test(timeout = 2000)
	public void test3() {
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("00000000") - 1) < DELTA);
		System.out.println("test 3 passed");
		
	}

	@Test(timeout = 2000)
	public void test4() {
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("10000000") - -1) < DELTA);
		System.out.println("test 4 passed");
		
	}

	@Test(timeout = 2000)
	public void test5() {
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("00001000") - 2) < DELTA);
		System.out.println("test 5 passed");
		
	}

	@Test(timeout = 2000)
	public void test6() {
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("00001100") - 3) < DELTA);
		System.out.println("test 6 passed");
		
	}

	@Test(timeout = 2000)
	public void test7(){
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("00111111") - 240) < DELTA);
		System.out.println("test 7 passed");
		
	}

	@Test(timeout = 2000)
	public void test8(){
		assertTrue(Math.abs(MiniFloat.miniFloatFromString("11111111") - -0.9375) < DELTA);
		System.out.println("test 8 passed");
		
	}

}
