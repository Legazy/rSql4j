package main.java.de.legazy.rsql4j;

import java.util.Arrays;

import main.java.cz.jirutka.rsql.parser.ast.ComparisonOperator;
import main.java.cz.jirutka.rsql.parser.ast.RSQLOperators;

public enum RSQLSearchOperation {

	EQUAL(RSQLOperators.EQUAL),
	NOT_EQUAL(RSQLOperators.NOT_EQUAL),
	LESS_THAN(RSQLOperators.LESS_THAN),
	LESS_OR_EQUAL(RSQLOperators.LESS_THAN_OR_EQUAL),
	GREATER_THAN(RSQLOperators.GREATER_THAN),
	GREATER_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL),
	IN(RSQLOperators.IN),
	NOT_IN(RSQLOperators.NOT_IN);
	
	private final ComparisonOperator operator;

	RSQLSearchOperation(final ComparisonOperator operator) {
		this.operator = operator;
	}
	
	public static RSQLSearchOperation getSimpleOperation(final ComparisonOperator operator) {
		   return Arrays.stream(values())
			          .filter(operation -> operation.getOperator() == operator)
			          .findAny().orElse(null);
	}

	public ComparisonOperator getOperator() {
		return this.operator;
	}
	
}
