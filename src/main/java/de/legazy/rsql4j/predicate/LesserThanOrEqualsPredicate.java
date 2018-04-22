package main.java.de.legazy.rsql4j.predicate;

public class LesserThanOrEqualsPredicate<T extends Comparable<T>> extends AbstractPredicate<T> implements Predicate<T> {

	@SuppressWarnings("unchecked")
	public LesserThanOrEqualsPredicate() {
		this.predicate = x -> x.compareTo((T) argument) < 0 || x.equals(argument);
	}

}
