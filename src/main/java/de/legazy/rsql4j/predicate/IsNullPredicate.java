package main.java.de.legazy.rsql4j.predicate;

import java.util.Optional;

public class IsNullPredicate<T> extends AbstractPredicate<T> implements Predicate<T> {
	
	public IsNullPredicate() {
		this.predicate =  x -> Optional.ofNullable(x).isPresent();
	}

}
