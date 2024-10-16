import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Stack;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class BalancedBracketsTest {

	@Test(timeout = 2000)
	public void test1() {
		assertTrue(BalancedBrackets.isBalanced("()"));
		
	}

	@Test(timeout = 2000)
	public void test2() {
		assertTrue(BalancedBrackets.isBalanced("(){}"));
		
	}

	@Test(timeout = 2000)
	public void test3() {
		assertTrue(BalancedBrackets.isBalanced("({})"));
		
	}

	@Test(timeout = 2000)
	public void test4() {
		assertFalse(BalancedBrackets.isBalanced("(("));
		
	}

	@Test(timeout = 2000)
	public void test5() {
		assertFalse(BalancedBrackets.isBalanced("({)}"));
		
	}

	@Test(timeout = 2000)
	public void test6(){
		assertFalse(BalancedBrackets.isBalanced("(){"));
		
	}

	@Test(timeout = 2000)
	public void test7() {
		assertFalse(BalancedBrackets.isBalanced(""));
		
	}

	@Test(timeout = 2000)
	public void test8() {
		assertFalse(BalancedBrackets.isBalanced("()[.]"));
		
	}

	@Test(timeout = 2000)
	public void test9() {
		assertTrue(BalancedBrackets.isBalanced("(()[{}])"));
		
	}

}
