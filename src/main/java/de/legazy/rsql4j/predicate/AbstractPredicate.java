package main.java.de.legazy.rsql4j.predicate;

public abstract class AbstractPredicate<T> implements Predicate<T>{

	T object;
	Object argument;
	
	protected java.util.function.Predicate<T> predicate; 
	
	@Override
	public Predicate<T> where(T object) {
		this.object = object;
		return this;
	}
	
	@Override
	public Predicate<T> setArgument(Object argument) {
		this.argument = argument;
		return this;
	}
	
	@Override
	public Predicate<T> not() {
		predicate = predicate.negate();
		return this;
	}
	
	@Override
	public boolean execute() {
		return predicate.test(this.object);
	}
	
	@Override
	public java.util.function.Predicate<T> getPredicate() {
		return this.predicate;
	}
	
	@Override
	public Predicate<T> and(Predicate<T> other) {
		this.predicate = this.predicate.and(other.getPredicate());
		return this;
	}
	
	@Override
	public Predicate<T> or(Predicate<T> other) {
		this.predicate = this.predicate.or(other.getPredicate());
		return this;
	}
	
}
