package christmas.controller;

import christmas.domain.order.Order;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import christmas.view.input.InputView;
import christmas.view.output.OrderView;
import java.util.Map;
import java.util.Map.Entry;

public class OrderController {

    private final InputView inputView = new InputView();
    private final OrderView orderView = new OrderView();

    public Order order() {
        Order order = makeOrder();
        orderView.printPreview();
        orderView.printMenu(order.getOrder());
        return order;
    }

    private Order makeOrder() {
        try {
            Map<String, Integer> menus = inputMenuOrder();
            return checkOrder(menus);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeOrder();
        }
    }

    private static Order checkOrder(Map<String, Integer> menus) {
        Order order = new Order();
        for (Entry<String, Integer> entry : menus.entrySet()) {
            order.plus(entry.getKey(), entry.getValue());
        }
        return order;
    }

    private Map<String, Integer> inputMenuOrder() {
        try {
            return inputView.readMenu();
        } catch (IllegalArgumentException e) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
    }
}
