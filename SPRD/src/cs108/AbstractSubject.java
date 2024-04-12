package cs108;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractSubject implements Subject {
    private final Set<Observer> observers = new HashSet<>();

    @Override
    public boolean addObserver(Observer o) {
        return this.observers.add(o);
    }

    @Override
    public boolean removeObserver(Observer o) {
        return this.observers.remove(o);
    }

    protected void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }
}
