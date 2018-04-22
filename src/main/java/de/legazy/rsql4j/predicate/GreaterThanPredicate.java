package main.java.de.legazy.rsql4j.predicate;

public class GreaterThanPredicate<T extends Comparable<T>> extends AbstractPredicate<T> implements Predicate<T>  {

	@SuppressWarnings("unchecked")
	public GreaterThanPredicate() {
		this.predicate = x -> x.compareTo((T) this.argument) > 1;
	}

}
