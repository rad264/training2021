package com.smbcgroup.training.calculator.dumb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.smbcgroup.training.calculator.Evaluator;
import com.smbcgroup.training.calculator.InvalidExpressionException;

public class DumbEvaluatorTest {

	private Evaluator evaluator = new DumbEvaluator();

	@Test
	public void testSingleDigitAddition() throws Exception {
		assertEquals(3, evaluator.evaluate("1+2"));
	}

	@Test
	public void testMultiDigitAddition() throws Exception {
		assertEquals(1221, evaluator.evaluate("1000+221"));
	}

	@Test
	public void testMultipleAdditions() throws Exception {
		assertEquals(17, evaluator.evaluate("1+2+5+9"));
	}

	@Test
	public void testSingleDigitSubtraction() throws Exception {
		assertEquals(4, evaluator.evaluate("5-1"));
	}

	@Test
	public void testMultiDigitSubtraction() throws Exception {
		assertEquals(999, evaluator.evaluate("1000-1"));
	}

	@Test
	public void testMultipleSubtractions() throws Exception {
		assertEquals(2, evaluator.evaluate("20-10-7-1"));
	}

	@Test
	public void testAdditionAndSubtraction() throws Exception {
		assertEquals(50, evaluator.evaluate("100+50-3+13-110"));
	}

	@Test(expected = InvalidExpressionException.class)
	public void testMissingFirstOperand() throws Exception {
		evaluator.evaluate("+2");
	}

	@Test(expected = InvalidExpressionException.class)
	public void testMissingMiddleOperand() throws Exception {
		evaluator.evaluate("1++2");
	}

	@Test(expected = InvalidExpressionException.class)
	public void testMissingLastOperand() throws Exception {
		evaluator.evaluate("1+1+");
	}

}
