package christmas.controller;

import christmas.domain.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;
import java.util.Map.Entry;

public class OrderController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public Order order() {
        Map<String, Integer> menus = inputMenuOrder();

        Order order = takeOrder(menus);
        outputView.printPreview();
        outputView.printMenu(order.getOrder());
        return order;
    }

    private static Order takeOrder(Map<String, Integer> menus) {
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
            System.out.println(e.getMessage());
            return inputMenuOrder();
        }
    }
}
