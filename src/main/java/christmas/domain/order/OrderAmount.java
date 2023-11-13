package christmas.domain.order;

import christmas.domain.menu.MenuCategory;
import java.util.Map;
import java.util.Map.Entry;

public class OrderAmount {

    private long totalAmount;

    private OrderAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public static OrderAmount of(Order order) {
        Map<MenuCategory, Integer> orderData = order.getOrder().order();
        long sum = 0;
        for (Entry<MenuCategory, Integer> entry : orderData.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return new OrderAmount(sum);
    }

    public long getTotalAmount() {
        return this.totalAmount;
    }
}
