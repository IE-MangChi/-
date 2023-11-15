package christmas.view.output;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.dto.MenuQuantityDto;
import java.util.Map.Entry;

public class EventView {

    public void printTotalAmount(final Long amount) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", amount);
    }

    public void printGiftMenu(final MenuQuantityDto gift) {
        System.out.println("\n<증정 메뉴>");
        if (gift == null) {
            System.out.println("없음");
            return;
        }
        for (Entry<MenuCategory, Integer> gifts : gift.order().entrySet()) {
            System.out.printf("%s %d개\n", gifts.getKey().getDish(), gifts.getValue());
        }
    }

    public void printDiscountResultIntro() {
        System.out.println("\n<혜택 내역>");

    }

    public void printDiscountEmpty() {
        System.out.println("없음");
    }

    public void printD_DayDiscount(final Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("크리스마스 디데이 할인: 0원");
            return;
        }
        System.out.printf("크리스마스 디데이 할인: -%,d원\n", amount);
    }

    public void printWeekdayDiscount(final Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("평일 할인: 0원");
            return;
        }
        System.out.printf("평일 할인: -%,d원\n", amount);
    }

    public void printWeekendDiscount(final Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("주말 할인: 0원");
            return;
        }
        System.out.printf("주말 할인: -%,d원\n", amount);
    }

    public void printSpecialDiscount(final Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("특별 할인: 0원");
            return;
        }
        System.out.printf("특별 할인: -%,d원\n", amount);
    }

    public void printGiftDiscount(final Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("증정 이벤트: 0원");
            return;
        }
        System.out.printf("증정 이벤트: -%,d원\n", amount);
    }

    public void printTotalBenefitAmount(final Long amount) {
        System.out.println("\n<총혜택 금액>");
        if (isAmountOverZero(amount)) {
            System.out.printf("-%,d원\n",amount);
            return;
        }
        System.out.printf("%,d원\n",amount);
    }

    private static boolean isAmountOverZero(final Long amount) {
        return amount > 0;
    }

    private static boolean isAmountZero(final Long amount) {
        return amount == 0;
    }
}
