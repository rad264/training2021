package com.smbcgroup.training.calculator.dumb;

import java.util.function.BiFunction;

import com.smbcgroup.training.calculator.Evaluator;
import com.smbcgroup.training.calculator.InvalidExpressionException;

public class DumbEvaluator implements Evaluator {

	@Override
	public Number evaluate(String expression) throws InvalidExpressionException {
		String[] operands = expression.split("\\+|\\-");
		String[] operators = expression.split("[^(\\+|\\-)]+");
		if (operands.length != operators.length)
			throw new InvalidExpressionException();
		
		Integer operand1 = parseInt(operands[0]);
		for (int i = 1; i < operands.length; i++) {
			Integer operand2 = parseInt(operands[i]);
			operand1 = parseOperator(operators[i]).apply(operand1, operand2);
		}
		return operand1;
	}

	private Integer parseInt(String string) throws InvalidExpressionException {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			throw new InvalidExpressionException();
		}
	}

	private BiFunction<Integer, Integer, Integer> parseOperator(String string) throws InvalidExpressionException {
		switch (string) {
		case "+":
			return (a, b) -> a + b;
		case "-":
			return (a, b) -> a - b;
		default:
			throw new InvalidExpressionException();
		}
	}

}
