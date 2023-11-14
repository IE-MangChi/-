package christmas.domain.event.discountEvent;

import christmas.domain.order.Order;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public enum WeekdayEvent implements Event<Integer, Long, Order>{

    WEEKDAY_EVENT(EventConfig.WEEKDAY_DISCOUNT_DAY_OF_WEEK);

    private List<DayOfWeek> discountWeekOfDay;

    WeekdayEvent(List<DayOfWeek> discountWeekOfDay) {
        this.discountWeekOfDay = discountWeekOfDay;
    }

    @Override
    public boolean support(Integer date) {
        LocalDate weekOfDay = LocalDate.of(EventConfig.YEAR, EventConfig.MONTH, date);
        DayOfWeek week = weekOfDay.getDayOfWeek();
        System.out.println(week);
        return discountWeekOfDay.stream()
                .anyMatch(day -> day.equals(week));
    }

    @Override
    public Long discount(Order order) {
        int discountMenuCount = order.getMenuByMenuNames(EventConfig.WEEKDAY_DISCOUNT_MENU);
        return discountMenuCount * EventConfig.WEEKDAY_DISCOUNT_AMOUNT;
    }
}
