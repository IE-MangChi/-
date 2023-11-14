package christmas.view.output;

import christmas.domain.event.designationEvent.BadgeEvent;

public class PayView {

    public void printAfterDiscountAmount(Long amount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(amount + "원");
    }

    public void printBadge(BadgeEvent badge) {
        System.out.println("\n<12월 이벤트 배지>");
        if (badge == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(badge.getBadge());
    }
}
