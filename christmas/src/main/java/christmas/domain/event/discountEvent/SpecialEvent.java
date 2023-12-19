package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;
import java.util.Arrays;
import java.util.List;

public enum SpecialEvent implements Event<Planner, Long> {

    STAR(EventConfig.SPECIAL_DISCOUNT_DAY);

    private final List<Integer> dayWithStar;

    SpecialEvent(final List<Integer> dayWithStar) {
        this.dayWithStar = dayWithStar;
    }


    @Override
    public boolean support(final Planner planner) {
        return dayWithStar.stream()
                .anyMatch(day -> day == planner.getDate());
    }

    @Override
    public Long discount(final Planner planner) {
        return EventConfig.SPECIAL_DISCOUNT_AMOUNT;
    }
}
