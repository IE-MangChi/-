package christmas.domain.event;

public interface Event<T, R, E> {

    boolean support(T value);

    R discount(E value);
}
