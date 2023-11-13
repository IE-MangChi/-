package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.OrderDto;
import java.util.HashMap;
import java.util.Map;

public class Order {

    private final Map<MenuCategory, Integer> orderItem = new HashMap<>();
    private long totalQuantity = 0L;

    private Order() {}

    private void put(final MenuCategory menuCategory, final Integer count) {
        orderItem.put(menuCategory, count);
    }

    public void plus(final String menu, final Integer count) {
        if (canOrder(count)) {
            MenuCategory dish = validateMenu(menu);
            put(dish, count);
            totalQuantity += count;
            return;
        }
        throw new IllegalArgumentException(
                "[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.\n"
                        + "(e.g. 시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1의 총개수는 7개)");
    }

    private static MenuCategory validateMenu(String menu) {
        try {
            MenuCategory dish = MenuCategory.getDishDataByName(menu);
            return dish;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private boolean canOrder(Integer count) {
        return totalQuantity + count <= OrderConfig.MAXIMUM_ORDER_AMOUNT;
    }

    public OrderDto getOrder() {
        return new OrderDto((Order) orderItem);
    }

    private
}
