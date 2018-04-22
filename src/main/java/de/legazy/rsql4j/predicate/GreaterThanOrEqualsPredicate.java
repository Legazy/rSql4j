package main.java.de.legazy.rsql4j.predicate;

public class GreaterThanOrEqualsPredicate<T extends Comparable<T>> extends AbstractPredicate<T> implements Predicate<T> {

	@SuppressWarnings("unchecked")
	public GreaterThanOrEqualsPredicate() {
		this.predicate = x -> x.compareTo((T) argument) > 1 || x.equals(argument);
	}

}
