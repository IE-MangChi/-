package christmas.domain.event;

public interface Event<T> {

    boolean support(T value);

    int discount(T value);
}
