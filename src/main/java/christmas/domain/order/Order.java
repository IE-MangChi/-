package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.MenuQuantityDto;
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
        validateZeroCount(count);
        MenuCategory dish = validateIsInMenu(menu);
        put(dish, count);
        totalQuantity += count;
        validateOrderCount(totalQuantity);
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

    private void put(final MenuCategory menuCategory, final Integer count) {
        orderItem.put(menuCategory, count);
    }

    private static MenuCategory validateIsInMenu(String menu) {
        Optional<MenuCategory> dish = MenuCategory.getDishDataByName(menu);
        if (dish.isEmpty()) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
        return dish.get();
    }

    private void validateOrderCount(long totalQuantity) {
        if (totalQuantity >= OrderConfig.MAXIMUM_ORDER_AMOUNT) {
            throw ChristmasException.of(ErrorMessage.MAXIMUM_MENU_QUANTITY_PER_ORDER);
        }
    }

    public MenuQuantityDto getOrder() {
        return new MenuQuantityDto(orderItem);
    }

    private void validateZeroCount(Integer count) {
        if (count.equals(0)) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
    }
}
