package main.java.de.legazy.rsql4j.predicate;

public class EqualsPredicate<T> extends AbstractPredicate<T> implements Predicate<T> {

	public EqualsPredicate() {
		this.predicate = x -> x.equals(this.argument);
	}

}
