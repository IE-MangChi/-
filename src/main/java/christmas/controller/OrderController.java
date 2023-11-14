package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.OrderAmount;
import christmas.domain.order.dto.OrderDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Map.Entry;

public class OrderController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public Order order() {
        Map<String, Integer> menus = inputView.readMenu();

        Order order = new Order();
        for (Entry<String, Integer> entry : menus.entrySet()) {
            order.plus(entry.getKey(), entry.getValue());
        }
        outputView.printMenu(order.getOrder());
        return order;
    }
}
