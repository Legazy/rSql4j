package main.java.de.legazy.rsql4j.predicate;

public interface Predicate<T> {

	public Predicate<T> where(T object);
	
	public Predicate<T> setArgument(Object argument);
	
	public Predicate<T> not();
	
	public boolean execute();
	
	public Predicate<T> and(Predicate<T> predicate);
	
	public Predicate<T> or(Predicate<T> other);
	
	public java.util.function.Predicate<T> getPredicate();
	
}