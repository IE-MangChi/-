package christmas.domain.event.discountEvent;

public interface Event<T, R, E> {

    boolean support(T value);

    R discount(E value);
}
