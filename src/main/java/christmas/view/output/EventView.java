package christmas.view.output;

import christmas.domain.menu.MenuCategory;

public class EventView {

    public void printTotalAmount(Long amount) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(amount);
    }

    public void printGiftMenu(MenuCategory gift) {
        System.out.println("\n<증정 메뉴>");
        if (gift == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(gift.getDish() + " 1개");
    }

    public void printDiscountResultIntro() {
        System.out.println("\n<혜택 내역>");

    }

    public void printDiscountEmpty() {
        System.out.println("없음");
    }

    public void printD_DayDiscount(Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("크리스마스 디데이 할인: 0원");
            return;
        }
        System.out.printf("크리스마스 디데이 할인: -%d원\n", amount);
    }

    public void printWeekdayDiscount(Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("평일 할인: 0원");
            return;
        }
        System.out.printf("평일 할인: -%d원\n", amount);
    }

    public void printWeekendDiscount(Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("주말 할인: 0원");
            return;
        }
        System.out.printf("주말 할인: -%d원\n", amount);
    }

    public void printSpecialDiscount(Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("특별 할인: 0원");
            return;
        }
        System.out.printf("특별 할인: -%d원\n", amount);
    }

    public void printGiftDiscount(Long amount) {
        if (isAmountZero(amount)) {
            System.out.println("증정 이벤트: 0원");
            return;
        }
        System.out.printf("증정 이벤트: -%d원\n", amount);
    }

    public void printTotalBenefitAmount(Long amount) {
        System.out.println("\n<총혜택 금액>");
        if (amount > 0) {
            System.out.printf("-%d원\n",amount);
            return;
        }
        System.out.printf("%d원\n",amount);
    }

    private static boolean isAmountZero(Long amount) {
        return amount == 0;
    }
}
