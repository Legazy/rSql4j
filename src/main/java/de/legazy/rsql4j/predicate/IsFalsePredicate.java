package main.java.de.legazy.rsql4j.predicate;

public class IsFalsePredicate<T> extends AbstractPredicate<T> implements Predicate<T> {
    public IsFalsePredicate() {
        this.predicate = x -> false;
    }
}
