package christmas.controller;

import christmas.domain.order.Order;
import christmas.domain.order.dto.AmountDto;
import christmas.view.InputView;
import christmas.view.OutputView;

public class MainController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final OrderController orderController;
    private final EventController eventController;
    private final PaymentController paymentController;

    public MainController() {
        this.orderController = new OrderController();
        this.eventController = new EventController();
        this.paymentController = new PaymentController();
    }

    public void server() {
        outputView.printIntro();
        int date = inputVisitDate();

        Order order = orderController.order();
        AmountDto discountedAmount = eventController.event(order, date);
        paymentController.pay(discountedAmount);
    }

    private int inputVisitDate() {
        try {
            return inputView.readDate();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputVisitDate();
        }
    }
}
