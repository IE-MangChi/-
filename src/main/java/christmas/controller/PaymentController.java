package christmas.controller;

import christmas.domain.event.designationEvent.BadgeEvent;
import christmas.domain.order.dto.AmountDto;
import christmas.view.OutputView;

public class PaymentController {

    private final OutputView outputView = new OutputView();

    public void pay(AmountDto amountdto) {
        outputView.printAfterDiscountAmount(amountdto.totalAmount()- amountdto.discount());
        outputView.printBadge(BadgeEvent.getBadgeByTotalBenefitAmount(amountdto.discount()));
    }
}
