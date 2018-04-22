package main.java.de.legazy.rsql4j.expression;

import java.util.ArrayList;
import java.util.List;

import main.java.cz.jirutka.rsql.parser.ast.ComparisonNode;
import main.java.cz.jirutka.rsql.parser.ast.LogicalNode;
import main.java.cz.jirutka.rsql.parser.ast.LogicalOperator;
import main.java.cz.jirutka.rsql.parser.ast.Node;

public class RSQLExpressionBuilder<T> {
	 
    public RSQLExpression<T> createPredicate(Node node) {
        if (node instanceof LogicalNode) {
            return createPredicate((LogicalNode) node);
        }
        if (node instanceof ComparisonNode) {
            return createPredicate((ComparisonNode) node);
        }
        return null;
    }
 
    public RSQLExpression<T> createPredicate(LogicalNode logicalNode) {
        List<RSQLExpression<T>> specs = new ArrayList<RSQLExpression<T>>();
        RSQLExpression<T> temp;
        for (Node node : logicalNode.getChildren()) {
            temp = createPredicate(node);
            if (temp != null) {
                specs.add(temp);
            }
        }
 
        RSQLExpression<T> result = specs.get(0);
        if (logicalNode.getOperator() == LogicalOperator.AND) {
            for (int i = 1; i < specs.size(); i++) {
                result = (RSQLExpression<T>) result.and(specs.get(i));
            }
        } else if (logicalNode.getOperator() == LogicalOperator.OR) {
            for (int i = 1; i < specs.size(); i++) {
                result = (RSQLExpression<T>) result.or(specs.get(i));
            }
        }
 
        return result;
    }
 
    public RSQLExpression<T> createPredicate(ComparisonNode comparisonNode) {
    	
    	RSQLExpression<T> specification = new RSQLExpression<T>(comparisonNode.getSelector(), comparisonNode.getOperator(), comparisonNode.getArguments());
    	
    	return specification;
    }
}
