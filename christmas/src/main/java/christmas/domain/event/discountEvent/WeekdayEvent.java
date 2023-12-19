package christmas.domain.event.discountEvent;

import christmas.domain.order.Planner;
import java.time.DayOfWeek;
import java.util.List;

public enum WeekdayEvent implements Event<Planner, Long> {

    WEEKDAY_EVENT(EventConfig.WEEKDAY_DISCOUNT_DAY_OF_WEEK);

    private final List<DayOfWeek> discountWeekOfDay;

    WeekdayEvent(final List<DayOfWeek> discountWeekOfDay) {
        this.discountWeekOfDay = discountWeekOfDay;
    }

    @Override
    public boolean support(final Planner planner) {
        return discountWeekOfDay.stream().anyMatch(day -> day.equals(planner.getWeekOfDay()));
    }

    @Override
    public Long discount(final Planner planner) {
        int discountMenuCount = planner.getMenuCountByMenuNames(EventConfig.WEEKDAY_DISCOUNT_MENU);
        return discountMenuCount * EventConfig.WEEKDAY_DISCOUNT_AMOUNT;
    }
}
