package main.java.de.legazy.rsql4j.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.lang.model.type.NullType;

import main.java.cz.jirutka.rsql.parser.ast.ComparisonOperator;
import main.java.de.legazy.rsql4j.RSQLSearchOperation;
import main.java.de.legazy.rsql4j.predicate.Predicate;
import main.java.de.legazy.rsql4j.predicate.PredicateBuilder;

public class RSQLExpression<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    private RSQLExpression<T> and;
    private RSQLExpression<T> or;

    public RSQLExpression(String property, ComparisonOperator operator, List<String> arguments) {
        this.property = property;
        this.operator = operator;
        this.arguments = arguments;
    }

    Predicate<?> toPredicate(RSQLExpressionParameter<T> root) {

        final PredicateBuilder builder = new PredicateBuilder();
        final List<Object> args = castArguments(root);
        final Object argument = args.get(0);

        if(!Optional.ofNullable(root.get(property)).isPresent())
            return builder.isFalse();

        switch (RSQLSearchOperation.getSimpleOperation(operator)) {
            case EQUAL:
                if (argument instanceof String) {
                    return builder.equal(root.get(property).toString().toLowerCase(), argument.toString().toLowerCase());
                } else if (argument == null) {
                    return builder.isNull(root.get(property));
                } else {
                    return builder.equal(root.get(property), argument);
                }
            case NOT_EQUAL:
                if (argument instanceof String) {
                    return builder.notEqual(root.get(property).toString().toLowerCase(), argument.toString().toLowerCase());
                } else if (argument == null) {
                    return builder.isNotNull(root.get(property));
                } else {
                    return builder.notEqual(root.get(property), argument);
                }
            case LIKE:

                return builder.like(root.get(property).toString().toLowerCase(), argument.toString().toLowerCase());
            case NOT_LIKE:
                return builder.notLike(root.get(property).toString().toLowerCase(), argument.toString().toLowerCase());
            case GREATER_THAN:
                return builder.greaterThan(root.get(property), (Comparable<?>) argument);
            case GREATER_OR_EQUAL:
                return builder.greaterOrEquals(root.get(property), (Comparable<?>) argument);
            case LESS_THAN:
                return builder.lesserThan(root.get(property), (Comparable<?>) argument);
            case LESS_OR_EQUAL:
                return builder.lesserOrEquals(root.get(property), (Comparable<?>) argument);
            case IN:
                return builder.in(root.get(property), args);
            case NOT_IN:
                return builder.notIn(root.get(property), args);
        }

        return builder.isFalse();
    }

    public boolean execute(T x) {

        RSQLExpressionParameter<T> root = new RSQLExpressionParameter<T>(x);

        if (Optional.ofNullable(this.and).isPresent()) {
            Predicate<?> t1 = this.toPredicate(root);
            Predicate<?> t2 = and.toPredicate(root);
            return t1.execute() && t2.execute();
        } else if (Optional.ofNullable(this.or).isPresent()) {
            Predicate<?> t1 = this.toPredicate(root);
            Predicate<?> t2 = or.toPredicate(root);
            return t1.execute() || t2.execute();
        } else {
            Predicate<?> t1 = this.toPredicate(root);
            return t1.execute();
        }
    }

    private List<Object> castArguments(final RSQLExpressionParameter<T> root) {
        final List<Object> args = new ArrayList<Object>();
        Optional<Object> object = Optional.ofNullable(root.get(property));
        final Class<? extends Object> type;

        if (object.isPresent())
            type = object.get().getClass();
        else
            type = NullType.class;

        for (final String argument : arguments) {
            if (type.equals(Integer.class)) {
                args.add(Integer.parseInt(argument));
            } else if (type.equals(Long.class)) {
                args.add(Long.parseLong(argument));
            } else if (type.equals(Double.class)) {
                args.add(Double.parseDouble(argument));
            } else if (type.equals(Boolean.class)) {
                args.add(Boolean.parseBoolean(argument));
            } else if (type.equals(NullType.class)) {
                args.add(null);
            } else {
                args.add(argument);
            }
        }

        return args;
    }


    public RSQLExpression<T> and(RSQLExpression<T> other) {
        this.and = other;
        return this;
    }

    public RSQLExpression<T> or(RSQLExpression<T> other) {
        this.or = other;
        return this;
    }
}
