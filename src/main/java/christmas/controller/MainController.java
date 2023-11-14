package christmas.controller;

import christmas.domain.order.Order;
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
        int date = inputView.readDate();

        Order order = orderController.order();
        eventController.event(order, date);

        long discountedAmount = eventController.event(order, date);
        paymentController.pay(discountedAmount);
    }

}
