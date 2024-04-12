package cs108;

public interface Subject {
    boolean addObserver(Observer o);

    boolean removeObserver(Observer o);
}
