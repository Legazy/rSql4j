package main.java.de.legazy.rsql4j.predicate;

import java.util.Collection;

public class InPredicate<T> extends AbstractPredicate<T> implements Predicate<T>  {

	public InPredicate() {
		this.predicate = x -> ((Collection<?>)argument).contains(x);
	}
}
