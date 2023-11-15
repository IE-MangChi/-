package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;
import java.time.DayOfWeek;
import java.util.List;

public enum WeekendEvent implements Event<Planner, Long> {

    WEEKEND_EVENT(EventConfig.WEEKEND_DISCOUNT_DAY_OF_WEEK);

    private final List<DayOfWeek> discountWeekOfDay;

    WeekendEvent(final List<DayOfWeek> discountWeekOfDay) {
        this.discountWeekOfDay = discountWeekOfDay;
    }

    @Override
    public boolean support(final Planner planner) {
        return discountWeekOfDay.stream().anyMatch(day -> day.equals(planner.getWeekOfDay()));
    }

    @Override
    public Long discount(final Planner planner) {
        int discountMenuCount = planner.getMenuCountByMenuNames(EventConfig.WEEKEND_DISCOUNT_MENU);
        return discountMenuCount * EventConfig.WEEKEND_DISCOUNT_AMOUNT;
    }
}
