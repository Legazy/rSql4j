package main.java.de.legazy.rsql4j.predicate;

import java.util.Collection;

public class PredicateBuilder {

	public <T> Predicate<T> equal(T object, Object argument) {
		return new EqualsPredicate<T>().where(object).setArgument(argument);
	}
	
	public <T> Predicate<T> like(T object, Object argument) {
		return new LikePredicate<T>().where(object).setArgument(argument);
	}
	
	public <T> Predicate<T> notLike(T object, Object argument) {
		return new LikePredicate<T>().where(object).setArgument(argument).not();
	}
	
	public <T extends Comparable<T>> Predicate<T> greaterThan(T object, Object argument) {
		return new GreaterThanPredicate<T>().where(object).setArgument(argument);
	}
	
	public <T extends Comparable<T>> Predicate<T> greaterOrEquals(T object, Object argument) {
		return new GreaterThanOrEqualsPredicate<T>().where(object).setArgument(argument);
	}
	
	public <T extends Comparable<T>> Predicate<T> lesserThan(T object, Object argument) {
		return new LesserThanPredicate<T>().where(object).setArgument(argument);
	}
	
	public <T extends Comparable<T>> Predicate<T> lesserOrEquals(T object, Object argument) {
		return new LesserThanOrEqualsPredicate<T>().where(object).setArgument(argument);
	}
	
	public <T> Predicate<T> notEqual(T object, Object argument) {
		return new EqualsPredicate<T>().where(object).setArgument(argument).not();
	}
	
	public <T> Predicate<T> in(T object, Collection<?> argument) {
		return new InPredicate<T>().where(object).setArgument(argument);
	}
	
	public <T> Predicate<T> notIn(T object, Collection<?> argument) {
		return new InPredicate<T>().where(object).not().setArgument(argument);
	}
	
	public <T> Predicate<T> isNull(T object) {
		return new IsNullPredicate<T>().where(object);
	}
	
	public <T> Predicate<T> isNotNull(T object) {
		return new IsNullPredicate<T>().where(object).not();
	}

}
