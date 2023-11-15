package christmas.controller;

import christmas.domain.order.Planner;
import christmas.domain.order.dto.AmountDto;
import christmas.view.output.OrderView;

public class MainController {

    private final OrderView orderView = new OrderView();
    private final OrderController orderController;
    private final EventController eventController;
    private final PaymentController paymentController;

    public MainController() {
        this.orderController = new OrderController();
        this.eventController = new EventController();
        this.paymentController = new PaymentController();
    }

    public void server() {
        orderView.printIntro();
        Planner planner = orderController.order();
        AmountDto discountedAmount = eventController.event(planner);
        paymentController.pay(discountedAmount);
    }
}
