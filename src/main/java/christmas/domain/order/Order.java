package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.MenuQuantityDto;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Order {

    private final Map<MenuCategory, Integer> orderItem;

    Order(final Map<MenuCategory, Integer> orders) {
        this.orderItem = orders;
    }

    public static Order of(final Map<String, Integer> orders) {
        HashMap<MenuCategory, Integer> store = new HashMap<>();
        long totalQuantity = 0L;
        for (Entry<String, Integer> menu : orders.entrySet()) {
            validateZeroCount(menu.getValue());
            store.put(validateIsInMenu(menu.getKey()), menu.getValue());
            totalQuantity += menu.getValue();
            validateOrderCount(totalQuantity);
        }
        return new Order(store);
    }

    private static MenuCategory validateIsInMenu(final String menu) {
        Optional<MenuCategory> dish = MenuCategory.getDishDataByName(menu);
        if (dish.isEmpty()) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
        return dish.get();
    }

    private static void validateOrderCount(long totalQuantity) {
        if (totalQuantity >= OrderConfig.MAXIMUM_ORDER_AMOUNT) {
            throw ChristmasException.of(ErrorMessage.MAXIMUM_MENU_QUANTITY_PER_ORDER);
        }
    }

    private static void validateZeroCount(final Integer count) {
        if (count.equals(0)) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
    }

    public int getMenuCountByMenuNames(final List<MenuCategory> menuNames) {
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

    public MenuQuantityDto getOrder() {
        return new MenuQuantityDto(orderItem);
    }

}
