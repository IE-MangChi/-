package christmas.domain.event;

public interface Event<T, R> {

    boolean support(T value);

    R discount(T value);
}
