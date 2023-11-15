package christmas.view.output;

import christmas.domain.event.designationEvent.BadgeEvent;

public class PayView {

    public void printAfterDiscountAmount(final Long amount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%,d원\n", amount);
    }

    public void printBadge(final BadgeEvent badge) {
        System.out.println("\n<12월 이벤트 배지>");
        if (badge == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(badge.getBadge());
    }
}
