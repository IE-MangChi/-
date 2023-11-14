package christmas.controller;

import christmas.domain.event.designationEvent.BadgeEvent;
import christmas.view.OutputView;

public class PaymentController {

    private final OutputView outputView = new OutputView();

    public void pay(Long amount) {
        outputView.printAfterDiscountAmount(amount);
        outputView.printBadge(BadgeEvent.getBadgeByTotalBenefitAmount(amount));
    }
}
