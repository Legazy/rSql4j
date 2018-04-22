package main.java.de.legazy.rsql4j.predicate;

public class LesserThanPredicate<T extends Comparable<T>> extends AbstractPredicate<T> implements Predicate<T> {

	@SuppressWarnings("unchecked")
	public LesserThanPredicate() {
		this.predicate = x -> x.compareTo((T) argument) < 0;
	}

}
