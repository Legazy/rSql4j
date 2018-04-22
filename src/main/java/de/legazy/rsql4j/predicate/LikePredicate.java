package main.java.de.legazy.rsql4j.predicate;

public class LikePredicate<T> extends AbstractPredicate<T> implements Predicate<T>  {

	public LikePredicate() {
		this.predicate = x -> ((String) argument).contains((String)x);
	}
	
}
