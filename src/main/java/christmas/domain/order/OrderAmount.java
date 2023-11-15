package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.MenuQuantityDto;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class OrderAmount {

    private final long totalAmount;

    private OrderAmount(final long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static OrderAmount of(final Order order) {
        Map<MenuCategory, Integer> orderData = validateOnlyBeverage(order.getOrder());
        long sum = 0;
        for (Entry<MenuCategory, Integer> entry : orderData.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return new OrderAmount(sum);
    }

    private static Map<MenuCategory, Integer> validateOnlyBeverage(final MenuQuantityDto menuQuantityDto) {
        Map<MenuCategory, Integer> order = menuQuantityDto.order();
        Optional<MenuCategory> findNotBeverage = order.keySet().stream().filter(menu -> !isBeverage(menu)).findFirst();
        if (findNotBeverage.isEmpty()) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
        return order;
    }

    private static boolean isBeverage(final MenuCategory menu) {
        return menu.equals(MenuCategory.CHAMPAGNE) | menu.equals(MenuCategory.ZERO_COLA) | menu.equals(
                MenuCategory.RED_WINE);
    }

    public long getTotalAmount() {
        return this.totalAmount;
    }
}
