package christmas.controller;

import christmas.domain.order.Planner;
import christmas.exception.ChristmasException;
import christmas.exception.ErrorMessage;
import christmas.view.input.InputView;
import christmas.view.output.OrderView;
import java.util.Map;

public class OrderController {

    private final InputView inputView = new InputView();
    private final OrderView orderView = new OrderView();

    public Planner order() {
        int date = inputVisitDate();
        Planner planner = makeOrder(date);
        orderView.printPreview();
        orderView.printMenu(planner.getOrder());
        return planner;
    }

    private Planner makeOrder(int date) {
        try {
            return Planner.of(inputMenuOrder(), date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeOrder(date);
        }
    }

    private int inputVisitDate() {
        try {
            return inputView.readDate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputVisitDate();
        }
    }

    private Map<String, Integer> inputMenuOrder() {
        try {
            return inputView.readMenu();
        } catch (IllegalArgumentException e) {
            throw ChristmasException.of(ErrorMessage.INVALID_ORDER);
        }
    }
}
