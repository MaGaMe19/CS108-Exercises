package cs108;

@FunctionalInterface
public interface Observer {
    void update(Subject s);
}
