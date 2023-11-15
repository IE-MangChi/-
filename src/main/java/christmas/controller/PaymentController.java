package christmas.controller;

import christmas.domain.event.designationEvent.BadgeEvent;
import christmas.domain.order.dto.AmountDto;
import christmas.view.output.PayView;

public class PaymentController {

    private final PayView payView = new PayView();

    public void pay(final AmountDto amountdto) {
        payView.printAfterDiscountAmount(amountdto.totalAmount() - amountdto.discount());
        payView.printBadge(BadgeEvent.getBadgeByTotalBenefitAmount(amountdto.discount()));
    }
}
