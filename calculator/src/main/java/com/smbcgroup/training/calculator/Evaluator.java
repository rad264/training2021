package com.smbcgroup.training.calculator;

public interface Evaluator {
	
	public Number evaluate(String expression) throws InvalidExpressionException;

}
