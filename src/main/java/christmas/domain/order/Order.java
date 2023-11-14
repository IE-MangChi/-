package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.OrderDto;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Order {

    private final Map<MenuCategory, Integer> orderItem = new HashMap<>();
    private long totalQuantity = 0L;

    public Order() {
    }

    public void plus(final String menu, final Integer count) {
        validateOrderCount(count);
        MenuCategory dish = validateIsInMenu(menu);
        put(dish, count);
        totalQuantity += count;
    }

    public int getMenuByMenuNames(List<MenuCategory> menuNames) {
        int sum = 0;
        for (MenuCategory menuName : menuNames) {
            if (orderItem.get(menuName) == null) {
                continue;
            }
            sum += orderItem.get(menuName);
        }
        return sum;
    }

    public OrderDto getOrder() {
        return new OrderDto(orderItem);
    }

    private static MenuCategory validateIsInMenu(String menu) {
        Optional<MenuCategory> dish = MenuCategory.getDishDataByName(menu);
        if (dish.isEmpty()) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
        return dish.get();
    }

    private void put(final MenuCategory menuCategory, final Integer count) {
        orderItem.put(menuCategory, count);
    }

    private void validateOrderCount(Integer count) {
        if (count >= OrderConfig.MAXIMUM_ORDER_AMOUNT) {
            throw ChristmasException.of(ErrorMessage.MAXIMUM_MENU_QUANTITY_PER_ORDER);
        }
    }
}
