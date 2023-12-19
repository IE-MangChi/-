package christmas.domain.order;

import christmas.domain.event.discountEvent.EventConfig;
import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.MenuQuantityDto;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Planner {

    private final Order order;
    private final int date;
    private final OrderAmount orderAmount;

    private Planner(final Order order, final int date, final OrderAmount orderAmount) {
        this.order = order;
        this.date = date;
        this.orderAmount = orderAmount;
    }

    public static Planner of(final Map<String, Integer> menus, final int date) {
        Order orders = Order.of(menus);
        return new Planner(orders, date, OrderAmount.of(orders));
    }

    public int getMenuCountByMenuNames(final List<MenuCategory> menuNames) {
        return order.getMenuCountByMenuNames(menuNames);
    }

    public long getTotalAmount() {
        return this.orderAmount.getTotalAmount();
    }

    public DayOfWeek getWeekOfDay() {
        LocalDate weekOfDay = LocalDate.of(EventConfig.YEAR, EventConfig.MONTH, this.date);
        return weekOfDay.getDayOfWeek();
    }

    public int getDate() {
        return this.date;
    }

    public MenuQuantityDto getOrder() {
        return order.getOrder();
    }
}
