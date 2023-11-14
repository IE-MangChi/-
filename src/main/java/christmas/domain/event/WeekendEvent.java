package christmas.domain.event;

import christmas.domain.order.Order;
import java.time.LocalDate;
import java.util.List;

public enum WeekendEvent implements Event<Integer, Long, Order>{

    WEEKDAY_EVENT(EventConfig.WEEKEND_DISCOUNT_DAY_OF_WEEK);

    private List<String> discountWeekOfDay;

    WeekendEvent(List<String> discountWeekOfDay) {
        this.discountWeekOfDay = discountWeekOfDay;
    }

    @Override
    public boolean support(Integer date) {
        LocalDate weekOfDay = LocalDate.of(EventConfig.YEAR, EventConfig.MONTH, date);
        return discountWeekOfDay.stream()
                .anyMatch(day -> day.equals(weekOfDay));
    }

    @Override
    public Long discount(Order order) {
        int discountMenuCount = order.getMenuByMenuNames(EventConfig.WEEKEND_DISCOUNT_MENU);
        return discountMenuCount * EventConfig.WEEKEND_DISCOUNT_AMOUNT;
    }
}
