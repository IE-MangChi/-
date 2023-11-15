package christmas.domain.event.discountEvent;

public interface Event<T, R> {

    boolean support(T value);

    R discount(T value);
}
