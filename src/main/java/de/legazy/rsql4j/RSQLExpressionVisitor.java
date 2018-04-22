package main.java.de.legazy.rsql4j;

import main.java.cz.jirutka.rsql.parser.ast.AndNode;
import main.java.cz.jirutka.rsql.parser.ast.ComparisonNode;
import main.java.cz.jirutka.rsql.parser.ast.OrNode;
import main.java.cz.jirutka.rsql.parser.ast.RSQLVisitor;
import main.java.de.legazy.rsql4j.expression.RSQLExpression;
import main.java.de.legazy.rsql4j.expression.RSQLExpressionBuilder;

public class RSQLExpressionVisitor<T> implements RSQLVisitor<RSQLExpression<T>, Void> {

	private RSQLExpressionBuilder<T> builder;
	
	public RSQLExpressionVisitor() {
		builder = new RSQLExpressionBuilder<T>();
	}
	
	@Override
	public RSQLExpression<T> visit(AndNode arg0, Void arg1) {
		return builder.createPredicate(arg0);
	}

	@Override
	public RSQLExpression<T> visit(OrNode arg0, Void arg1) {
		return builder.createPredicate(arg0);
	}

	@Override
	public RSQLExpression<T> visit(ComparisonNode arg0, Void arg1) {
		return builder.createPredicate(arg0);
	}

}
